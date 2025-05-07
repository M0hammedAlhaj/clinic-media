package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.user.User;

import java.util.Optional;

public interface UserRepository<T extends User> {

    Optional<T> findUserByEmail(String email);

    void saveUser(T user);
}
