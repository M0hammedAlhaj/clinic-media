package com.spring.clinicmedia.domain.model;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private Registration registration;

    public CustomUserDetail(Registration registration) {
        this.registration = registration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return registration.getPassword();
    }

    @Override
    public String getUsername() {
        return registration.getEmail();
    }
}
