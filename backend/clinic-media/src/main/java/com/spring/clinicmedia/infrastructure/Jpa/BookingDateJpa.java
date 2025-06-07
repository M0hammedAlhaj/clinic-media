package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDateJpa extends JpaRepository<BookingDate, Long> {

    @Query("""
           \s
                      SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
                      FROM BOOKING_DATES b\s
                      JOIN b.doctor d
                  WHERE d.userId = :doctorId\s
            AND b.bookingDateStarting < :endDate\s
            AND b.bookingDateEnding > :startDate
           \s""")
    boolean existsOverlappingBookingByDoctorId(
            @Param("doctorId") Long doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    Optional<List<BookingDate>> findBookingDatesByDoctorUserIdAndClinicUserIdAndBookingDateStatus(Long doctorId, Long clinicId, BookingDateState status);

    Optional<List<BookingDate>> findBookingDateByClinicUserIdAndBookingDateStatus(Long clinicId, BookingDateState status);
}
