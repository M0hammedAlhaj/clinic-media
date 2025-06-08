package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import com.spring.clinicmedia.domain.port.repository.AdminRepository;
import com.spring.clinicmedia.infrastructure.Jpa.AdminJpa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminRepositoryAdapterTest {


    private AutoCloseable closeable;

    @Mock
    private AdminJpa adminJpa;

    private AdminRepository underTest;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        underTest = new AdminRepositoryAdapter(adminJpa);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void findUserByEmail() {
        //when
        underTest.findUserByEmail("email");

        //then
        verify(adminJpa)
                .findByRegistrationEmail("email");
    }

    @Test
    void saveUser() {
        underTest.saveUser(new Admin());
        verify(adminJpa)
                .save(new Admin());

    }

    @Test
    void getUserByActive() {
        underTest.getUserByActive(true, Pageable.ofSize(5));
        verify(adminJpa)
                .findByIsActive(true, Pageable.ofSize(5));
    }

    @Test
    void getAdminById_shouldReturnAdminIfExists() {
        // Given
        Admin admin = new Admin();
        admin.setUserId(1L);

        // Mock behavior
        when(adminJpa.findById(1L))
                .thenReturn(Optional.of(admin));

        // When
        Admin result = underTest.getUserByIdOrElseThrow(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getUserId());

        verify(adminJpa)
                .findById(1L);
    }


    @Test
    void getAdminById_adminNotFoundAndThrowException() {

        when(adminJpa.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByIdOrElseThrow(1L))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(adminJpa).findById(1L);
    }

    @Test
    void getAdminByEmail_shouldReturnAdminIfExists() {

        Admin admin = new Admin();
        admin.setRegistration(Registration.builder()
                .email("admin@gmai.com")
                .build());

        when(adminJpa.findByRegistrationEmail(admin.getRegistration().getEmail()))
                .thenReturn(Optional.of(admin));


        Admin result = underTest.getUserByUserEmailOrElseThrow(admin.getRegistration().getEmail());

        assertThat(result).isNotNull();

        verify(adminJpa).findByRegistrationEmail(admin.getRegistration().getEmail());

    }

    @Test
    void getAdminByEmail_adminNotFoundAndThrowException() {

        when(adminJpa.findByRegistrationEmail("admin@gmail.com"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByUserEmailOrElseThrow("admin@gmail.com"))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(adminJpa)
                .findByRegistrationEmail("admin@gmail.com");

    }

    @Test
    void existByUserId() {
        underTest.existsByUserId(1L);
        verify(adminJpa)
                .existsById(1L);
    }
}