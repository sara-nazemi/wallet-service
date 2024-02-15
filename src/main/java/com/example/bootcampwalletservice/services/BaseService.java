package com.example.bootcampwalletservice.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseService <E,R extends JpaRepository<E,Long>>{
    E insert(E entity);
    void update(E entity);
    void deleteById(Long id);
    E findById(Long id);
    List<E> findAll();
    List<E> findByExample(E entity);
}
