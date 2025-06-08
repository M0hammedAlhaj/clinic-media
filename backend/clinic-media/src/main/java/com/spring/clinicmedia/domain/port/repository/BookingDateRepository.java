package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookingDateRepository extends BaseRepository<BookingDate, Long> {

    boolean existsBookingDateByDoctorIdAndStartDateBetweenEndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<BookingDate> getByDoctorClinicAndStatusOrElseThrow(Long doctorId, Long clinicId, BookingDateState status);

    List<BookingDate> findByClinicAndStatus(Long clinicId, BookingDateState status);
}
