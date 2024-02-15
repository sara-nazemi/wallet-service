package com.example.bootcampwalletservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseServiceImpl<E, R extends JpaRepository<E, Long>> implements BaseService<E, R> {
    @Autowired
    protected R repository;

    @Override
    public E insert(E entity) {
        return repository.save(entity);
    }

    @Override
    public void update(E entity) {

        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    @Override
    public E findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public List<E> findByExample(E entity) {
        return repository.findAll(Example.of(entity));
    }
}
