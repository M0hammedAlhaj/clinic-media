package com.spring.clinicmedia.application.request;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service responsible for handling requests between doctors and clinics.
 * <p>
 * This class enables a doctor to send a request to a clinic (or vice versa),
 * validates activation status, ensures no duplicate association exists,
 * and saves the request with status {@link RequestStatus#PENDING}.
 * </p>
 *
 * <p><b>Usage example:</b></p>
 * <pre>{@code
 * doctorClinicRequestService.createRequest(doctorId, clinicId, UserType.DOCTOR);
 * }</pre>
 *
 * @author YourName
 */
@Service
@AllArgsConstructor
public class DoctorClinicRequestService {

    private final RequestRepository requestRepository;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final RequestValidator validator;
    private final UserActivationValidator userActivationValidator;

    /**
     * Creates a clinic-doctor request from the specified sender.
     *
     * @param doctorId   the unique ID of the doctor
     * @param clinicId   the unique ID of the clinic
     * @param senderType the type of user initiating the request (DOCTOR or CLINIC)
     * @throws ResourceAlreadyExistsException                                         if the doctor is already associated with the clinic
     * @throws com.spring.clinicmedia.domain.exception.ResourcesNotFoundException     if doctor or clinic is not found
     * @throws com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException if a request already exists
     */
    @Transactional
    public void createRequest(long doctorId,
                              long clinicId,
                              UserType senderType) {

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);
        Doctor doctor = doctorRepository.getUserByIdOrElseThrow(doctorId);

        userActivationValidator.validateUserIsActive(doctorId, UserType.DOCTOR);

        userActivationValidator.validateUserIsActive(clinicId, UserType.CLINIC);

        if (doctorRepository.isDoctorAssociatedWithClinic(doctorId, clinicId)) {
            throw new ResourceAlreadyExistsException("Doctor is already associated with the clinic.");
        }

        validator.validateRequestDoesNotExist(clinicId, doctorId);

        ClinicDoctorRequest clinicDoctorRequest = buildRequest(doctor, clinic, senderType);

        requestRepository.save(clinicDoctorRequest);
    }

    /**
     * Helper method to build a new {@link ClinicDoctorRequest} object.
     *
     * @param doctor     the doctor entity
     * @param clinic     the clinic entity
     * @param senderType the sender's user type (DOCTOR or CLINIC)
     * @return a new ClinicDoctorRequest with {@link RequestStatus#PENDING}
     */
    private ClinicDoctorRequest buildRequest(Doctor doctor,
                                             Clinic clinic,
                                             UserType senderType) {
        return ClinicDoctorRequest.builder()
                .clinic(clinic)
                .doctor(doctor)
                .sender(senderType)
                .status(RequestStatus.PENDING)
                .build();
    }
}

