package com.akybenko.employee.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonService<T> {
    T get(Long id);
    T create(T entity);
    List<T> findAll();
    T update(Long id, T entity);
    ResponseEntity delete(Long id);
}
