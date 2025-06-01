package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.infrastructure.Jpa.BookingDateJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class BookingDateRepositoryAdapter implements BookingDateRepository {

    private final BookingDateJpa bookingDateJpa;

    @Override
    public BookingDate save(BookingDate entity) {
        return bookingDateJpa.save(entity);
    }

    @Override
    public BookingDate getById(Long id) {
        return bookingDateJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Booking date not found"));
    }

    @Override
    public boolean existsBookingDateByDoctorIdAndStartDateBetweenEndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingDateJpa.existsOverlappingBookingByDoctorId(doctorId, startDate, endDate);
    }
}
