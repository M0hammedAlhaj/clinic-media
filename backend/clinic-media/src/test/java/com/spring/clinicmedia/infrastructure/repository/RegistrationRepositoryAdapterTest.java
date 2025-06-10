package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.infrastructure.Jpa.RegistrationJpa;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationRepositoryAdapterTest {

    @InjectMocks
    private RegistrationRepositoryAdapter underTest;

    @Mock
    private RegistrationJpa repository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void findByEmail_shouldReturnOptional() {
        Registration registration = new Registration();
        when(repository.findByEmail("test@example.com")).thenReturn(Optional.of(registration));

        Optional<Registration> result = underTest.findByEmail("test@example.com");

        assertThat(result).isPresent();
        verify(repository).findByEmail("test@example.com");
    }

    @Test
    void getByEmail_shouldReturnRegistration() {
        Registration registration = new Registration();
        when(repository.findByEmail("test@example.com")).thenReturn(Optional.of(registration));

        Registration result = underTest.getByEmail("test@example.com");

        assertThat(result).isEqualTo(registration);
        verify(repository).findByEmail("test@example.com");
    }

    @Test
    void getByEmail_shouldThrowExceptionIfNotFound() {
        when(repository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getByEmail("notfound@example.com"))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessageContaining("Email not found");

        verify(repository).findByEmail("notfound@example.com");
    }
}
