package com.spring.clinicmedia.domain.repository.user;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;

public interface DoctorRepository extends UserRepository<Doctor> {

    boolean isDoctorAssociatedWithClinic(long doctorId, long clinicId);

}
