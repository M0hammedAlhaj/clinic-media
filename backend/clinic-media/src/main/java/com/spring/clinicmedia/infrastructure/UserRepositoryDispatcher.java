package com.spring.clinicmedia.infrastructure;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepositoryDispatcher {

    private final Map<UserType, UserRepository<? extends User>> maps = new HashMap<>();

    @Autowired
    public UserRepositoryDispatcher(
            AdminRepository adminRepo,
            ClinicRepository clinicRepo,
            LabRepository labRepo,
            DoctorRepository doctorRepo,
            PatientRepository patientRepo) {

        maps.put(UserType.ADMIN, adminRepo);
        maps.put(UserType.CLINIC, clinicRepo);
        maps.put(UserType.LAB, labRepo);
        maps.put(UserType.DOCTOR, doctorRepo);
        maps.put(UserType.PATIENT, patientRepo);
    }

    @SuppressWarnings("unchecked")
    public <T extends User> UserRepository<T> getRepository(UserType type) {
            if (!maps.containsKey(type)) {
                throw new UnsupportedOperationException();
            }
        return (UserRepository<T>) maps.get(type);
    }
}

