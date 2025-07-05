package com.spring.clinicmedia.domain.repository;

import com.spring.clinicmedia.domain.model.enitity.Registration;

import java.util.Optional;

public interface RegistrationRepository {

    Optional<Registration> findByEmail(String email);

    Registration getByEmail(String email);
}
