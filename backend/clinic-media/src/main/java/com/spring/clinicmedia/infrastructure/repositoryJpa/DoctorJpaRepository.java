package com.spring.clinicmedia.infrastructure.repositoryJpa;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DoctorJpaRepository extends JpaRepository<Doctor, Integer> {


    Optional<Doctor> findByRegistrationEmail(String email);
}
