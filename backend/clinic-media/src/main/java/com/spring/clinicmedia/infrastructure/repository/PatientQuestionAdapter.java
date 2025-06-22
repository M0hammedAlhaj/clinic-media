package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.PatientQuestion;
import com.spring.clinicmedia.domain.repository.PatientQuestionRepository;
import com.spring.clinicmedia.infrastructure.Jpa.PatientQuestionJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientQuestionAdapter implements PatientQuestionRepository {

    private PatientQuestionJpa patientQuestionJpa;

    @Override
    public PatientQuestion save(PatientQuestion entity) {
        return patientQuestionJpa.save(entity);
    }

    @Override
    public PatientQuestion getByIdOrElseThrow(Long id) {
        return patientQuestionJpa.findById(id).orElseThrow(() ->
                new ResourcesNotFoundException(PatientQuestion.class, id));
    }

    @Override
    public boolean existsById(Long id) {
        return patientQuestionJpa.existsById(id);
    }
}
