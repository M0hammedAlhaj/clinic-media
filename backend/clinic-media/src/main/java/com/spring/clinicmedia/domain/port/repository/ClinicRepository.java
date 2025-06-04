package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends UserRepository<Clinic> {

    List<Clinic> getClinicsByFilter(Pageable pageable, Specification<Clinic> specification);

    Optional<Clinic> findClinicByIdAndSpecialityName(String specialityName, Long clinicId);
}
