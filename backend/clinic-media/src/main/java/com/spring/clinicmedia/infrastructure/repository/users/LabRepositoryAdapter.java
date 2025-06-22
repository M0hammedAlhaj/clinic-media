package com.spring.clinicmedia.infrastructure.repository.users;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import com.spring.clinicmedia.domain.port.repository.user.LabRepository;
import com.spring.clinicmedia.infrastructure.Jpa.users.LabJpa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LabRepositoryAdapter implements LabRepository {

    private final LabJpa labJpa;


    @Override
    public Optional<Lab> findUserByEmail(String email) {
        return labJpa.findLabByRegistrationEmail(email);
    }

    @Override
    public Lab saveUser(Lab user) {
        return labJpa.save(user);
    }

    public Page<Lab> getUserByActive(boolean active, Pageable pageable) {
        return labJpa.findByIsActive(active, pageable);
    }

    @Override
    public Lab getUserByIdOrElseThrow(Long id) {
        return labJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.LAB, id));

    }

    @Override
    public Lab getUserByUserEmailOrElseThrow(String userEmail) {
        return labJpa.findLabByRegistrationEmail(userEmail).orElseThrow(() -> new ResourcesNotFoundException(Lab.class, userEmail));
    }

    @Override
    public boolean existsByUserId(Long id) {
        return labJpa.existsById(id);
    }

}

