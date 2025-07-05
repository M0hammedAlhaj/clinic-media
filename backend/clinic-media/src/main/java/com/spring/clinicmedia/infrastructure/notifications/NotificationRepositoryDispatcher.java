package com.spring.clinicmedia.infrastructure.notifications;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.repository.notifications.NotificationRepository;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationClinicJpa;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationDoctorJpa;
import com.spring.clinicmedia.infrastructure.Jpa.notifcations.NotificationPatientJpa;
import com.spring.clinicmedia.infrastructure.repository.notifcations.NotificationClinicAdapter;
import com.spring.clinicmedia.infrastructure.repository.notifcations.NotificationDoctorAdapter;
import com.spring.clinicmedia.infrastructure.repository.notifcations.NotificationPatientAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationRepositoryDispatcher {

    private final Map<UserType, NotificationRepository<? extends Notification>> maps
            = new HashMap<>();

    public NotificationRepositoryDispatcher(NotificationClinicJpa notificationClinicJpa,
                                            NotificationPatientJpa notificationPatientJpa,
                                            NotificationDoctorJpa notificationDoctorJpa) {

        maps.put(UserType.CLINIC, new NotificationClinicAdapter(notificationClinicJpa));
        maps.put(UserType.PATIENT, new NotificationPatientAdapter(notificationPatientJpa));
        maps.put(UserType.DOCTOR, new NotificationDoctorAdapter(notificationDoctorJpa));
    }


    @SuppressWarnings("unchecked")
    public <T extends Notification> NotificationRepository<T> getRepository(UserType userType) {

        if (!maps.containsKey(userType)) {
            throw new UnsupportedOperationException();
        }
        return (NotificationRepository<T>) maps.get(userType);

    }
}
