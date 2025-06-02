package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ClinicRepository extends UserRepository<Clinic> {

    List<Clinic> getClinicsByFilter(Pageable pageable, Specification<Clinic> specification);

}
