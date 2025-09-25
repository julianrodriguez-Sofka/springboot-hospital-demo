package com.clinica.management.exception;

import lombok.Getter;

@Getter
public class DuplicatedDataException extends RuntimeException {
    private final String entity;
    private final String dni;
    public DuplicatedDataException(String entity, String dni) {

        super(String.format("%s with DNI %s already exists",entity,dni  ));
        this.entity = entity;
        this.dni = dni;
    }
}
