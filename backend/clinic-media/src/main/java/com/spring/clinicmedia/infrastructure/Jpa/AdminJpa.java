package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AdminJpa extends JpaRepository<Admin,Long > {


    Optional<Admin> findByRegistrationEmail(String email);


    Page<Admin> findByIsActive(boolean active, Pageable pageable);

    Admin getAdminsByRegistrationEmail(String userEmail );
}
