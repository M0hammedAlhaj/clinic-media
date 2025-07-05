package com.spring.clinicmedia.infrastructure.repository.users;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import com.spring.clinicmedia.domain.repository.user.AdminRepository;
import com.spring.clinicmedia.infrastructure.Jpa.users.AdminJpa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminRepositoryAdapter implements AdminRepository {

    private final AdminJpa adminJpa;

    @Override
    public Optional<Admin> findUserByEmail(String email) {
        return adminJpa.findByRegistrationEmail(email);
    }

    @Override
    public Admin saveUser(Admin user) {
        return adminJpa.save(user);
    }

    public Page<Admin> getUserByActive(boolean active, Pageable pageable) {
        return adminJpa.findByIsActive(active, pageable);
    }

    @Override
    public Admin getUserByIdOrElseThrow(Long id) {
        return adminJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(Admin.class, id));
    }

    @Override
    public Admin getUserByUserEmailOrElseThrow(String userEmail) {
        return adminJpa.findByRegistrationEmail(userEmail)
                .orElseThrow(() ->
                        new ResourcesNotFoundException(Admin.class, userEmail));
    }

    @Override
    public boolean existsByUserId(Long id) {
        return adminJpa.existsById(id);
    }

}
