package com.spring.clinicmedia.presentation.coinfigration;

import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.port.repository.RegistrationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailedService implements UserDetailsService {

    private final RegistrationRepository repository;

    public CustomUserDetailedService(RegistrationRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

