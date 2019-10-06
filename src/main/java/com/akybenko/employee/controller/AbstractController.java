package com.akybenko.employee.controller;

import com.akybenko.employee.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractController<E, S extends CommonService<E>> {

    private S service;

    AbstractController(S service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<E>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<E> create(@Valid @RequestBody E entity) {
        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Long id, @Valid @RequestBody E entity) {
        return new ResponseEntity<>(service.update(id, entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        return service.delete(id);
    }
}
