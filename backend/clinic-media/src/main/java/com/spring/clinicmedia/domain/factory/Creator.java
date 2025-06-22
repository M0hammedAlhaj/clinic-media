package com.spring.clinicmedia.domain.factory;

public interface Creator<T, U> {

    T create(U u);

}
