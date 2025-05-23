package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;

public interface DoctorRepository extends UserRepository<Doctor> {

    boolean existsDoctorInClinic(long doctorId,long clinicId);

}
