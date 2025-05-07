package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import com.spring.clinicmedia.domain.port.repository.AdminRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.AdminJpaRepository;
import lombok.AllArgsConstructor;
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
}
