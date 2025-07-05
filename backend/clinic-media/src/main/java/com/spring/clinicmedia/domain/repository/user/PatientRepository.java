package com.spring.clinicmedia.domain.repository.user;

import com.spring.clinicmedia.domain.model.enitity.user.Patient;

public interface PatientRepository extends UserRepository<Patient> {


    boolean isPatientHasInsurance(Long patientId, String insuranceName);
}
