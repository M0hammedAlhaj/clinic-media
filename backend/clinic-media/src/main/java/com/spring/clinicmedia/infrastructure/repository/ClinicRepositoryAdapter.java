package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.infrastructure.Jpa.ClinicJpa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicRepositoryAdapter implements ClinicRepository {

    private final ClinicJpa clinicJpa;


    @Override
    public Optional<Clinic> findUserByEmail(String email) {
        return clinicJpa.findClinicByRegistrationEmail(email);
    }

    @Override
    public Clinic saveUser(Clinic user) {
       return clinicJpa.save(user);
    }

    @Override
    public Page<Clinic> getUserByActive(boolean active, Pageable pageable) {
        return clinicJpa.findByIsActive(active, pageable);
    }

    @Override
    public Clinic getUserByIdOrElseThrow(Long id) {
        return clinicJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.CLINIC, id));
    }

    @Override
    public Clinic getUserByUserEmailOrElseThrow(String userEmail) {
        return clinicJpa.findClinicByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(Clinic.class, userEmail));
    }

    @Override
    public boolean existsByUserId(Long id) {
        return clinicJpa.existsById(id);
    }

    @Override
    public List<Clinic> getClinicsByFilter(Pageable pageable,
                                           Specification<Clinic> specification) {
        Page<Clinic> clinicsPage = clinicJpa.findAll(specification, pageable);

        if (clinicsPage.isEmpty()) {
            throw new ResourcesNotFoundException(Clinic.class, specification.toString());
        }
        return clinicsPage.getContent();
    }


    @Override
    public Optional<Clinic> findClinicByIdAndSpecialityName(Long clinicId, String specialityName ) {
        return clinicJpa.searchBySpecialitiesSpecialityNameAndUserId(specialityName, clinicId);
    }

    @Override
    public boolean isClinicHasInsurance(Long clinicId, String insuranceName) {
        return clinicJpa.existsClinicByUserIdAndInsurancesInsuranceName(clinicId,insuranceName);
    }

}

