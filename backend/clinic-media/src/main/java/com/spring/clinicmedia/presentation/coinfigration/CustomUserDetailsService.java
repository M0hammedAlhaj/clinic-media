package com.spring.clinicmedia.presentation.coinfigration;

import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.repository.RegistrationRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepositoryDispatcher userRepositoryDispatcher;

    private final RegistrationRepository registrationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Registration registration = registrationRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        CustomUserDetail userDetail = new CustomUserDetail(registration);
        userDetail.setUserRepositoryDispatcher(userRepositoryDispatcher);

        return userDetail;
    }
}


