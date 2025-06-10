package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.infrastructure.Jpa.RequestJpa;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestRepositoryAdapterTest {

    @InjectMocks
    private RequestRepositoryAdapter underTest;

    @Mock
    private RequestJpa requestJpa;

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
    void save_shouldCallJpaAndReturnSavedEntity() {
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        when(requestJpa.save(request)).thenReturn(request);

        ClinicDoctorRequest result = underTest.save(request);

        assertThat(result).isEqualTo(request);
        verify(requestJpa).save(request);
    }

    @Test
    void getByIdOrElseThrow_shouldReturnEntity() {
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        when(requestJpa.findById(1L)).thenReturn(Optional.of(request));

        ClinicDoctorRequest result = underTest.getByIdOrElseThrow(1L);

        assertThat(result).isEqualTo(request);
        verify(requestJpa).findById(1L);
    }

    @Test
    void getByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        when(requestJpa.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getByIdOrElseThrow(1L))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessageContaining("Request not found");

        verify(requestJpa).findById(1L);
    }

    @Test
    void existsById_shouldReturnTrue() {
        when(requestJpa.existsById(1L)).thenReturn(true);

        assertThat(underTest.existsById(1L)).isTrue();
        verify(requestJpa).existsById(1L);
    }

    @Test
    void findByClinicIdAndDoctorIdAndSender_shouldReturnOptional() {
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        when(requestJpa.findByClinicUserIdAndDoctorUserIdAndSender(1L, 2L, UserType.CLINIC))
                .thenReturn(Optional.of(request));

        Optional<ClinicDoctorRequest> result = underTest.findByClinicIdAndDoctorIdAndSender(1L, 2L, UserType.CLINIC);

        assertThat(result).isPresent().contains(request);
        verify(requestJpa).findByClinicUserIdAndDoctorUserIdAndSender(1L, 2L, UserType.CLINIC);
    }

    @Test
    void getRequestsBySenderIdAndSenderType_shouldReturnClinicRequests() {
        Pageable pageable = PageRequest.of(0, 5);
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        Page<ClinicDoctorRequest> page = new PageImpl<>(List.of(request));

        when(requestJpa.findRequestsByClinicUserIdAndSender(1L, UserType.CLINIC, pageable)).thenReturn(page);

        List<ClinicDoctorRequest> result = underTest.getRequestsBySenderIdAndSenderType(1L, UserType.CLINIC, pageable);

        assertThat(result).containsExactly(request);
        verify(requestJpa).findRequestsByClinicUserIdAndSender(1L, UserType.CLINIC, pageable);
    }

    @Test
    void getRequestsBySenderIdAndSenderType_shouldReturnDoctorRequests() {
        Pageable pageable = PageRequest.of(0, 5);
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        Page<ClinicDoctorRequest> page = new PageImpl<>(List.of(request));

        when(requestJpa.findRequestsByDoctorUserIdAndSender(1L, UserType.DOCTOR, pageable)).thenReturn(page);

        List<ClinicDoctorRequest> result = underTest.getRequestsBySenderIdAndSenderType(1L, UserType.DOCTOR, pageable);

        assertThat(result).containsExactly(request);
        verify(requestJpa).findRequestsByDoctorUserIdAndSender(1L, UserType.DOCTOR, pageable);
    }

    @Test
    void findByClinicIdAndDoctorID_shouldReturnOptional() {
        ClinicDoctorRequest request = new ClinicDoctorRequest();
        when(requestJpa.findByClinicUserIdAndDoctorUserId(1L, 2L)).thenReturn(Optional.of(request));

        Optional<ClinicDoctorRequest> result = underTest.findByClinicIdAndDoctorID(1L, 2L);

        assertThat(result).isPresent().contains(request);
        verify(requestJpa).findByClinicUserIdAndDoctorUserId(1L, 2L);
    }
}
