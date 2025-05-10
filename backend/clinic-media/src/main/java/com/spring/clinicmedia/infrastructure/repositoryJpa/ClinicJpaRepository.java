package com.spring.clinicmedia.infrastructure.repositoryJpa;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClinicJpaRepository extends JpaRepository<Clinic, Integer> {


    Optional<Clinic> findClinicByRegistrationEmail(String email);

    Page<Clinic> findByIsActive(boolean active, Pageable pageable);
}
