package com.spring.clinicmedia.domain;

public interface NotificationSender {

    boolean isNotify (String message, String receiverUserName);
}
