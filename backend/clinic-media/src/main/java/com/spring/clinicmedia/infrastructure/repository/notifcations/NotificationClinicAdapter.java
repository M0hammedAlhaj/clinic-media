package com.spring.clinicmedia.infrastructure.repository.notifcations;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationClinic;
import com.spring.clinicmedia.domain.repository.notifications.NotificationClinicRepository;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationClinicJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationClinicAdapter implements NotificationClinicRepository {

    private final NotificationClinicJpa notificationClinicJpa;


    @Override
    public NotificationClinic save(NotificationClinic entity) {
        return notificationClinicJpa.save(entity);
    }

    @Override
    public NotificationClinic getByIdOrElseThrow(Long id) {
        return notificationClinicJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(NotificationClinic.class, id));
    }


    @Override
    public boolean existsById(Long id) {
        return notificationClinicJpa.existsById(id);
    }
}
