package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceJpa extends JpaRepository<Insurance, String> {


}