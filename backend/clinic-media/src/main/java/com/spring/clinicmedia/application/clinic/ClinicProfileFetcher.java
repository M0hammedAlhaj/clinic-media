package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.repository.user.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for fetching the profile of a clinic by its user ID.
 */
@AllArgsConstructor
@Service
public class ClinicProfileFetcher {

    private final ClinicRepository clinicUserRepository;

    /**
     * Retrieves the clinic profile for the given user ID.
     *
     * @param userId the ID of the clinic user
     * @return the Clinic entity associated with the given user ID
     * @throws ResourcesNotFoundException if no clinic with the given user ID exists
     */
    public Clinic execute(long userId) {
        return clinicUserRepository.getUserByIdOrElseThrow(userId);
    }
}
