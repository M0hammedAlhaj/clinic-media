    package com.spring.clinicmedia.infrastructure.repository;

    import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
    import com.spring.clinicmedia.domain.model.BookingDateState;
    import com.spring.clinicmedia.domain.model.enitity.BookingDate;
    import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
    import com.spring.clinicmedia.infrastructure.Jpa.BookingDateJpa;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;

    import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
    import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;


    class BookingDateRepositoryAdapterTest {

        private BookingDateRepository underTest;

        @Mock
        private BookingDateJpa bookingDateJpaMock;

        private AutoCloseable closeable;

        @BeforeEach
        void setUp() {
            closeable = MockitoAnnotations.openMocks(this);
            underTest = new BookingDateRepositoryAdapter(bookingDateJpaMock);
        }

        @AfterEach
        void tearDown() throws Exception {
            closeable.close();
        }

        @Test
        void save() {
            BookingDate entity = new BookingDate();
            underTest.save(entity);

            verify(bookingDateJpaMock).save(entity);
        }

        @Test
        void getByIdOrElseThrow_shouldReturnBookingDate() {

            when(bookingDateJpaMock.findById(1L))
                    .thenReturn(Optional.of(new BookingDate()));

            assertThat(underTest.getByIdOrElseThrow(1L)).isNotNull();

            verify(bookingDateJpaMock).findById(1L);
        }

        @Test
        void getByIdOrElseThrow_shouldThrowException() {

            when(bookingDateJpaMock.findById(1L))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.getByIdOrElseThrow(1L))
                    .isInstanceOf(ResourcesNotFoundException.class);

            verify(bookingDateJpaMock).findById(1L);
        }


        @Test
        void existsById() {

            underTest.existsById(1L);

            verify(bookingDateJpaMock).existsById(1L);
        }

        @Test
        void existsBookingDateByDoctorIdAndStartDateBetweenEndDate() {

            LocalDateTime startDate = LocalDateTime.now();
            LocalDateTime endDate = LocalDateTime.now();

            underTest.existsBookingDateByDoctorIdAndStartDateBetweenEndDate(1L,
                    startDate,
                    endDate);

            verify(bookingDateJpaMock).existsOverlappingBookingByDoctorId(1L,
                    startDate,
                    endDate);
        }

        @Test
        void getByDoctorClinicAndStatusOrElseThrow_shouldReturnBookingDate() {

            when(bookingDateJpaMock
                    .findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus
                            (1L, 1L, BookingDateState.AWAITING_CONFIRMATION))
                    .thenReturn(Optional.of(List.of(new BookingDate())));

            assertThat(underTest.getByDoctorClinicAndStatusOrElseThrow(1L, 1L, BookingDateState.AWAITING_CONFIRMATION))
                    .isNotNull();

            verify(bookingDateJpaMock).findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus
                    (1L, 1L, BookingDateState.AWAITING_CONFIRMATION);

        }

        @Test
        void getByDoctorClinicAndStatusOrElseThrow_shouldThrowException() {

            when(bookingDateJpaMock
                    .findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus
                            (1L, 1L, BookingDateState.AWAITING_CONFIRMATION))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.getByDoctorClinicAndStatusOrElseThrow(1L, 1L, BookingDateState.AWAITING_CONFIRMATION))
                    .isInstanceOf(ResourcesNotFoundException.class);

            verify(bookingDateJpaMock).findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus
                    (1L, 1L, BookingDateState.AWAITING_CONFIRMATION);

        }

        @Test
        void findByClinicAndStatus_shouldReturnBookingDate() {

            when(bookingDateJpaMock
                    .findBookingDateByClinicUserIdAndBookingDateStatus(1L, BookingDateState.AWAITING_CONFIRMATION))
                    .thenReturn(Optional.of(List.of(new BookingDate())));

            assertThat(underTest.findByClinicAndStatus(1L, BookingDateState.AWAITING_CONFIRMATION))
                    .isNotNull();

            verify(bookingDateJpaMock).findBookingDateByClinicUserIdAndBookingDateStatus(1L, BookingDateState.AWAITING_CONFIRMATION);

        }


        @Test
        void findByClinicAndStatus_shouldReturnBookingDate_shouldThrowException() {

            when(bookingDateJpaMock
                    .findBookingDateByClinicUserIdAndBookingDateStatus(1L, BookingDateState.AWAITING_CONFIRMATION))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.findByClinicAndStatus(1L, BookingDateState.AWAITING_CONFIRMATION))
                    .isInstanceOf(ResourcesNotFoundException.class);

            verify(bookingDateJpaMock).findBookingDateByClinicUserIdAndBookingDateStatus(1L, BookingDateState.AWAITING_CONFIRMATION);

        }

    }