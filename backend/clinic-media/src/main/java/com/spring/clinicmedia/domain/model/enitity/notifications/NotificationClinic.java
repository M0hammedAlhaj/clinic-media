package com.spring.clinicmedia.domain.model.enitity.notifications;


import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "NOTIFICATION_CLINIC")
@AttributeOverride(
        name = "notificationId",
        column = @Column(name = "notification_clinic_id")
)
@Data
public class NotificationClinic extends Notification {

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

}
