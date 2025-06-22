package com.spring.clinicmedia.domain.repository;

import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingDateRepository extends BaseRepository<BookingDate, Long> {

    boolean existsBookingDateByDoctorIdAndStartDateBetweenEndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<BookingDate> getByDoctorClinicAndStatusOrElseThrow(Long doctorId, Long clinicId, BookingDateState status);

    List<BookingDate> findByClinicAndStatus(Long clinicId, BookingDateState status);
}
