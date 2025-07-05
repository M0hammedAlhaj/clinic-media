package com.spring.clinicmedia.infrastructure.repository.notifcations;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationDoctor;
import com.spring.clinicmedia.domain.repository.notifications.NotificationDoctorRepository;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationDoctorJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationDoctorAdapter implements NotificationDoctorRepository {

    private final NotificationDoctorJpa notificationDoctorJpa;


    @Override
    public NotificationDoctor save(NotificationDoctor entity) {
        return notificationDoctorJpa.save(entity);
    }

    @Override
    public NotificationDoctor getByIdOrElseThrow(Long id) {
        return notificationDoctorJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(NotificationDoctor.class, id));
    }


    @Override
    public boolean existsById(Long id) {
        return notificationDoctorJpa.existsById(id);
    }
}
