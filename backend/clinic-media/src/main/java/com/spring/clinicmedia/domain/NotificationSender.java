package com.spring.clinicmedia.domain;

public interface NotificationSender {

    void notify (String message, String userName);
}
