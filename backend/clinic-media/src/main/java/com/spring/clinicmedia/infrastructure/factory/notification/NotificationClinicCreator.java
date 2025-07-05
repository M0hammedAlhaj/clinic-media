package com.spring.clinicmedia.infrastructure.factory.notification;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificNotificationCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationClinic;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.infrastructure.mapping.NotificationCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationClinicCreator implements SpecificNotificationCreator<Clinic> {

    @Override
    public UserType getUserType() {
        return UserType.CLINIC;
    }


    @Override
    public Notification create(NotificationCreationCommand<Clinic> command) {
        NotificationClinic notificationClinic = new NotificationClinic();
        NotificationCreationCommandMapper.assignData(command, notificationClinic);
        notificationClinic.setClinic(command.getUser());
        return notificationClinic;
    }


}
