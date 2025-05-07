package com.spring.clinicmedia.infrastructure.factory;

public interface Creator<T, U> {

    T create(U u);

}
