package com.spring.clinicmedia.domain.port.repository;

public interface BaseRepository<T,U> {

     T save(T entity);

     T getByIdOrElseThrow(U id);

     boolean existsById(U id);

}
