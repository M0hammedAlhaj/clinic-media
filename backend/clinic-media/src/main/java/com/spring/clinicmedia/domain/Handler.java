package com.spring.clinicmedia.domain;

public interface Handler<T> {

    void handle(T t );
}
