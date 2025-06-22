package com.spring.clinicmedia.domain.model.enitity.notifications;


import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import jakarta.persistence.*;


@Entity
@Table(name = "NOTIFICATION_DOCTOR")
@AttributeOverride(
        name = "notificationId",
        column = @Column(name = "notification_doctor_id")
)
public class NotificationDoctor extends Notification {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
