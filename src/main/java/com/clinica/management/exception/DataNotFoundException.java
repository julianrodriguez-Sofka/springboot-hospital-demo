package com.clinica.management.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    //Patient not found >> Patient con ID X not found
    private final Long id;
    private final String entity;


    public DataNotFoundException(Long id, String entity) {
        super(String.format("%s with ID %d not found", entity, id));
        this.id = id;
        this.entity = entity;
    }

}
