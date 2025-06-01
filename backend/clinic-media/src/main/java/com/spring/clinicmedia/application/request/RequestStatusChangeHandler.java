package com.spring.clinicmedia.application.request;

import com.spring.clinicmedia.domain.exception.request.RequestStatusChangeNotAllowedException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RequestStatusChangeHandler {

    private final RequestRepository requestRepository;

    private final DoctorRepository doctorRepository;

    private final ClinicRepository clinicRepository;

    @Transactional
    public void execute(long requestId, long userChangeId, UserType userChangeStatus, RequestStatus requestStatus) {

        Request request = requestRepository.getById(requestId);


        boolean isDoctor = userChangeStatus.equals(UserType.DOCTOR);
        boolean isClinic = userChangeStatus.equals(UserType.CLINIC);
        boolean isNotSender = !request.getSender().equals(userChangeStatus);
        boolean isChanged = false;

        Doctor doctor = doctorRepository.getUserById(request.getDoctor().getUserId());
        Clinic clinic = clinicRepository.getUserById(request.getClinic().getUserId());

        if (isDoctor && isNotSender) {
            if (request.getDoctor().getUserId().equals(userChangeId)) {
                request.setStatus(requestStatus);
                isChanged = true;
            }
        }

        if (isClinic && isNotSender) {
            if (request.getClinic().getUserId().equals(userChangeId)) {
                request.setStatus(requestStatus);
                isChanged = true;
            }
        }

        if (isChanged &&
                requestStatus.equals(RequestStatus.APPROVE)) {
            clinic.getDoctors().add(doctor);
        }

        if (!isChanged) {
            throw new RequestStatusChangeNotAllowedException("You are not allowed to change the status of this request.");
        }
    }
}
