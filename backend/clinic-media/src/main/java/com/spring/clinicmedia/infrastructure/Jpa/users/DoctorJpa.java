package com.spring.clinicmedia.infrastructure.Jpa.users;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DoctorJpa extends JpaRepository<Doctor, Long> {


    Optional<Doctor> findByRegistrationEmail(String email);

    Page<Doctor> findByIsActive(boolean active, Pageable pageable);


@Query("SELECT CASE WHEN EXISTS (" +
            "SELECT 1 FROM Clinic c JOIN c.doctors d " +
            "WHERE c.userId = :clinicId AND d.userId = :doctorId) THEN true ELSE false END")
    boolean isDoctorAssociatedWithClinic(@Param("doctorId") long doctorId,
                                         @Param("clinicId") long clinicId);

}
