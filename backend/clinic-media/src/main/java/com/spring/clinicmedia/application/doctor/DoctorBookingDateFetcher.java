package com.spring.clinicmedia.application.doctor;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.user.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorBookingDateFetcher {

    private final DoctorRepository doctorRepository;

    private final BookingDateRepository bookingDateRepository;

    private final ClinicRepository clinicRepository;

    public Map<Doctor, List<BookingDate>> execute(Long clinicId,
                                                  BookingDateState bookingDateState) {

        if (!clinicRepository.existsByUserId(clinicId)) {
            throw new ResourcesNotFoundException(Clinic.class, clinicId);
        }


        return bookingDateRepository.findByClinicAndStatus(clinicId, bookingDateState)
                .stream()
                .collect(Collectors.groupingBy(BookingDate::getDoctor));
    }

}
