package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories(basePackageClasses = Lab.class)
public interface LabJpa extends JpaRepository<Lab, Long> {


    Optional<Lab> findLabByRegistrationEmail(String email);

    Page<Lab> getLabsByActive(boolean active, Pageable pageable);

    Page<Lab> findByIsActive(boolean active, Pageable pageable);
}
