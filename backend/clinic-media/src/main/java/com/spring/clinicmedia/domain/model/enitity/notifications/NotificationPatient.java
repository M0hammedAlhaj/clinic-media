package com.spring.clinicmedia.domain.model.enitity.notifications;


import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import jakarta.persistence.*;


@Entity
@Table(name = "NOTIFICATION_PATIENTS") // or your actual table name
@AttributeOverride(
        name = "notificationId",
        column = @Column(name = "notification_patient_id")
)
public class NotificationPatient extends Notification {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
