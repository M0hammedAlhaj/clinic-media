package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.infrastructure.Jpa.ClinicJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicRepositoryAdapter implements ClinicRepository {

    private final ClinicJpaRepository clinicJpaRepository;


    @Override
    public Optional<Clinic> findUserByEmail(String email) {
        return clinicJpaRepository.findClinicByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Clinic user) {
        clinicJpaRepository.save(user);
    }

    @Override
    public Page<Clinic> getUserByActive(boolean active, Pageable pageable) {
        return clinicJpaRepository.findByIsActive(active, pageable);
    }

    @Override
    public Clinic getUserById(Long id) {
        return clinicJpaRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.CLINIC, id));
    }

    @Override
    public Clinic getUserByUserEmail(String userEmail) {
        return clinicJpaRepository.findClinicByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.CLINIC, userEmail));
    }

    @Override
    public List<Clinic> getClinicsByFilter(Pageable pageable,
                                           Specification<Clinic> specification) {
        Page<Clinic> clinicsPage = clinicJpaRepository.findAll(specification, pageable);

        if (clinicsPage.isEmpty()) {
            throw new ResourcesNotFoundException(UserType.CLINIC, specification.toString());
        }

        return clinicsPage.getContent();
    }

}

