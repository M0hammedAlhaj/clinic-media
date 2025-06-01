package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.Insurance;

import java.util.Optional;

public interface InsuranceRepository extends BaseRepository<Insurance,String> {

    Optional<Insurance> findInsuranceByInsuranceNumber(String insuranceNumber);
}
