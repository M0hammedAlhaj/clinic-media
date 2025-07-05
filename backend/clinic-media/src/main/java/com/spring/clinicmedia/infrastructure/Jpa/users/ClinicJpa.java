package com.spring.clinicmedia.infrastructure.Jpa.users;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClinicJpa extends JpaRepository<Clinic, Long>, JpaSpecificationExecutor<Clinic> {


    Optional<Clinic> findClinicByRegistrationEmail(String email);

    Page<Clinic> findByIsActive(boolean active, Pageable pageable);


    Optional<Clinic> searchBySpecialitiesSpecialityNameAndUserId(String specialityName, Long clinicId);

    boolean existsClinicByUserIdAndInsurancesInsuranceName(Long clinicId, String insuranceName);
}
