package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.DoctorJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorRepositoryAdapter implements DoctorRepository {

    private final DoctorJpaRepository doctorJpaRepository;

    @Override
    public Optional<Doctor> findUserByEmail(String email) {
        return doctorJpaRepository.findByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Doctor user) {
        doctorJpaRepository.save(user);
    }

}
