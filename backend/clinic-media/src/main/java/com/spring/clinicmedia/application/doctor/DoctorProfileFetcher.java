package com.spring.clinicmedia.application.doctor;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.user.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for fetching the profile of a doctor by user ID.
 */
@Service
@AllArgsConstructor
public class DoctorProfileFetcher {

    private final DoctorRepository doctorUserRepository;

    /**
     * Retrieves the doctor profile for the given user ID.
     *
     * @param userId the ID of the doctor user
     * @return the Doctor entity associated with the given user ID
     * @throws ResourcesNotFoundException if no doctor with the given user ID exists
     */
    public Doctor execute(long userId) {
        return doctorUserRepository.getUserByIdOrElseThrow(userId);
    }
}
