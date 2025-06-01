package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;

import java.time.LocalDateTime;

public interface BookingDateRepository extends BaseRepository<BookingDate,Long> {

    boolean existsBookingDateByDoctorIdAndStartDateBetweenEndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

}
