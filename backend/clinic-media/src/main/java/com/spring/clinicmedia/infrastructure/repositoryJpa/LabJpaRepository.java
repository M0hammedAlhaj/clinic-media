package com.spring.clinicmedia.infrastructure.repositoryJpa;

import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories(basePackageClasses = Lab.class)
public interface LabJpaRepository extends JpaRepository<Lab, Long> {


    Optional<Lab> findLabByRegistrationEmail(String email);
}
