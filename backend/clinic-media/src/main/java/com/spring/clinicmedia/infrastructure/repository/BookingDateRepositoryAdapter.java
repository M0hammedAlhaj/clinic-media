package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.infrastructure.Jpa.BookingDateJpa;
import org.springframework.stereotype.Component;

@Component
public class BookingDateRepositoryAdapter implements BookingDateRepository {

    private BookingDateJpa bookingDateJpa;

    @Override
    public BookingDate save(BookingDate entity) {
        return bookingDateJpa.save(entity);
    }
}
