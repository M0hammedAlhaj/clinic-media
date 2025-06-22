package com.spring.clinicmedia.infrastructure.repository.notifcations;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationClinic;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationPatient;
import com.spring.clinicmedia.domain.repository.notifications.NotificationPatientRepository;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationPatientJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationPatientAdapter implements NotificationPatientRepository {

    private final NotificationPatientJpa notificationPatientJpa;


    @Override
    public NotificationPatient save(NotificationPatient entity) {
        return notificationPatientJpa.save(entity);
    }

    @Override
    public NotificationPatient getByIdOrElseThrow(Long id) {
        return notificationPatientJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(NotificationClinic.class, id));
    }


    @Override
    public boolean existsById(Long id) {
        return notificationPatientJpa.existsById(id);
    }
}
