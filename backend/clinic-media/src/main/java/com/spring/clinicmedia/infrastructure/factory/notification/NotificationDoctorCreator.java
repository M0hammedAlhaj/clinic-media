package com.spring.clinicmedia.infrastructure.factory.notification;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificNotificationCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationDoctor;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.infrastructure.mapping.NotificationCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationDoctorCreator implements SpecificNotificationCreator<Doctor> {

    @Override
    public UserType getUserType() {
        return UserType.DOCTOR;
    }

    @Override
    public Notification create(NotificationCreationCommand<Doctor> command) {
        NotificationDoctor notificationDoctor = new NotificationDoctor();
        NotificationCreationCommandMapper.assignData(command, notificationDoctor);
        notificationDoctor.setDoctor(command.getUser());

        return notificationDoctor;
    }

}
