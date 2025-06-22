package com.spring.clinicmedia.domain.port.repository.user;

import com.spring.clinicmedia.domain.model.enitity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository<T extends User> {

    Optional<T> findUserByEmail(String email);

    T saveUser(T user);

    Page<T> getUserByActive(boolean active, Pageable pageable);

    T getUserByIdOrElseThrow(Long id);

    T getUserByUserEmailOrElseThrow(String userEmail);

    boolean existsByUserId(Long id);
}
