package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.infrastructure.Jpa.users.ClinicJpa;
import com.spring.clinicmedia.infrastructure.Jpa.users.DoctorJpa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@DataJpaTest
class DoctorJpaTest {

    @Autowired
    private DoctorJpa underTest;

    @Autowired
    private ClinicJpa clinicJpa;
    @Autowired
    private DoctorJpa doctorJpa;


    @AfterEach
    public void tearDown() {
        clinicJpa.deleteAll();
        doctorJpa.deleteAll();
    }
    @Transactional
    @Test
    void isDoctorAssociatedWithClinic_returnTrue() {

        Clinic clinic = new Clinic();

        Doctor doctor = new Doctor();
        clinic.setDoctors(List.of(doctor));
        clinic = clinicJpa.save(clinic);
        doctor = underTest.save(doctor);

        clinicJpa.flush();
        doctorJpa.flush();

        assertThat(underTest.isDoctorAssociatedWithClinic(doctor.getUserId(), clinic.getUserId()))
                .isTrue();

    }

    @Transactional
    @Test
    void isDoctorAssociatedWithClinic_returnFalse() {

        Clinic clinic = new Clinic();

        Doctor doctor = new Doctor();

        clinic = clinicJpa.save(clinic);
        doctor = underTest.save(doctor);

        clinicJpa.flush();
        doctorJpa.flush();

        assertThat(underTest.isDoctorAssociatedWithClinic(doctor.getUserId(), clinic.getUserId()))
                .isFalse();

    }
}