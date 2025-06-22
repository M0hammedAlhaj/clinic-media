package com.spring.clinicmedia.infrastructure.factory.notification;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificNotificationCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationPatient;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.infrastructure.mapping.NotificationCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationPatientCreator implements SpecificNotificationCreator<Patient> {

    @Override
    public UserType getUserType() {
        return UserType.PATIENT;
    }

    @Override
    public Notification create(NotificationCreationCommand<Patient> command) {
        NotificationPatient notificationPatient = new NotificationPatient();
        NotificationCreationCommandMapper.assignData(command, notificationPatient);
        notificationPatient.setPatient(command.getUser());
        return notificationPatient;
    }


}
