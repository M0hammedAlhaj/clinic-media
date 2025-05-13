package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import com.spring.clinicmedia.domain.port.repository.AdminRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.AdminJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminRepositoryAdapter implements AdminRepository {

    private final AdminJpaRepository adminJpaRepository;

    @Override
    public Optional<Admin> findUserByEmail(String email) {
        return adminJpaRepository.findByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Admin user) {
        adminJpaRepository.save(user);
    }

    public Page<Admin> getUserByActive(boolean active, Pageable pageable) {
        return adminJpaRepository.findByIsActive(active, pageable);
    }

    @Override
    public Admin getUserById(Long id) {
        return adminJpaRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.ADMIN, id));
    }

    @Override
    public Admin getUserByUserEmail(String userEmail) {
        return adminJpaRepository.findByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.ADMIN, userEmail));
    }

}
