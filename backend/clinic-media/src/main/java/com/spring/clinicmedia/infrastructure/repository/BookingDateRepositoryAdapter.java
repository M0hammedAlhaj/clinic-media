package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.infrastructure.Jpa.BookingDateJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookingDateRepositoryAdapter implements BookingDateRepository {

    private final BookingDateJpa bookingDateJpa;

    @Override
    public BookingDate save(BookingDate entity) {
        return bookingDateJpa.save(entity);
    }

    @Override
    public BookingDate getByIdOrElseThrow(Long id) {
        return bookingDateJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Booking date not found"));
    }

    @Override
    public boolean existsById(Long id) {
        return bookingDateJpa.existsById(id);
    }

    @Override
    public boolean existsBookingDateByDoctorIdAndStartDateBetweenEndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingDateJpa.existsOverlappingBookingByDoctorId(doctorId, startDate, endDate);
    }

    @Override
    public List<BookingDate> findByDoctorClinicAndStatus(Long doctorId, Long clinicId, BookingDateState status) {
        return bookingDateJpa.
                findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus(doctorId, clinicId, status)
                .orElseThrow(() -> new ResourcesNotFoundException("Booking date not found"));
    }


    @Override
    public Map<Doctor, List<BookingDate>> findByClinicAndStatus(Long clinicId, BookingDateState status) {

        List<BookingDate> bookingDates =
                bookingDateJpa.findBookingDateByClinicUserIdAndBookingDateStatus(clinicId, status)
                        .orElseThrow(() -> new ResourcesNotFoundException("Booking date not found"));

        return bookingDates.stream()
                .collect(Collectors.groupingBy(BookingDate::getDoctor));

    }

}
