package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.infrastructure.Jpa.DoctorJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Doctor saveUser(Doctor user) {
       return doctorJpaRepository.save(user);
    }

    public Page<Doctor> getUserByActive(boolean active, Pageable pageable) {
        return doctorJpaRepository.findByIsActive(active, pageable);
    }

    @Override
    public Doctor getUserByIdOrElseThrow(Long id) {
        return doctorJpaRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.DOCTOR, id));
    }

    @Override
    public Doctor getUserByUserEmailOrElseThrow(String userEmail) {
        return doctorJpaRepository.findByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(Doctor.class, userEmail));
    }

    @Override
    public boolean existsByUserId(Long id) {
        return doctorJpaRepository.existsById(id);
    }

    @Override
    public boolean existsDoctorInClinic(long doctorId,long clinicId) {
        return doctorJpaRepository.existsDoctorByClinicId(doctorId,clinicId);
    }

}
