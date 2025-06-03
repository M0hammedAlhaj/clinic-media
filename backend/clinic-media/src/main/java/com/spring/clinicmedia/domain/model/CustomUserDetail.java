package com.spring.clinicmedia.domain.model;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.UserRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Setter
public class CustomUserDetail implements UserDetails {

    private final Registration registration;

    private UserRepositoryDispatcher userRepositoryDispatcher;

    public CustomUserDetail(Registration registration) {
        this.registration = registration;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + registration.getType();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        return List.of(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return registration.getPassword();
    }

    @Override
    public String getUsername() {
        return registration.getEmail();
    }

    public Long getUserId() {
        UserRepository<User> userRepository = userRepositoryDispatcher.getRepository(registration.getType());
        return userRepository.
                getUserByUserEmail(registration.getEmail()).getUserId();
    }

    public boolean isActive() {
        UserRepository<User> userRepository = userRepositoryDispatcher.getRepository(registration.getType());
        return userRepository.
                getUserByUserEmail(registration.getEmail()).isActive();
    }
}
