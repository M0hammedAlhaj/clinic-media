package com.spring.clinicmedia.domain.port;

public interface Handler<T> {

    void handle(T t );
}
