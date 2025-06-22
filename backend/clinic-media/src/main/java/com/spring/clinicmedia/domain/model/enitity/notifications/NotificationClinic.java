package com.spring.clinicmedia.domain.model.enitity.notifications;


import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import jakarta.persistence.*;


@Entity
@Table(name = "NOTIFICATION_CLINIC") // or your actual table name
@AttributeOverride(
        name = "notificationId",
        column = @Column(name = "notification_clinic_id")
)
public class NotificationClinic extends Notification {

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

}
