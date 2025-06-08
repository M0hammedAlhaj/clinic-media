package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface RegistrationJpa extends JpaRepository<Registration, Long> {

        Optional<Registration> findByEmail(String email);
}
