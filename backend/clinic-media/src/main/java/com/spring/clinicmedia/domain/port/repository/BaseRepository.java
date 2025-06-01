package com.spring.clinicmedia.domain.port.repository;

public interface BaseRepository<T,U> {

     T save(T entity);

     T getById(U id);

}
