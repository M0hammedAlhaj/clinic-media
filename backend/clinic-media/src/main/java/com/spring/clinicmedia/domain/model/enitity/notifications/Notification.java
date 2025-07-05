package com.spring.clinicmedia.domain.model.enitity.notifications;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String message;

    @Column(name = "updated_at")
    private LocalDateTime createdAt;

    @Column(name = "is_receive")
    private boolean isReceive;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isReceive = true;
    }

}
