package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.port.repository.SpecialityRepository;
import com.spring.clinicmedia.infrastructure.Jpa.SpecialityJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpecialityRepositoryAdapter implements SpecialityRepository {

    private final SpecialityJpa specialityJpa;

    @Override
    public Speciality save(Speciality entity) {
        return specialityJpa.save(entity);
    }

    @Override
    public Speciality getByIdOrElseThrow(String id) {
        return specialityJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(Speciality.class, id));
    }

    @Override
    public boolean existsById(String id) {
        return specialityJpa.existsById(id);
    }
}
