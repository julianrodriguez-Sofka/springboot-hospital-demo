package com.clinica.management.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //3. Validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidations(MethodArgumentNotValidException ex){
        //1. Define el mapa de errores como el que tiene Validation Error Response
        Map<String, String> errors = new HashMap<>();

        //2. Captura de los errores que ocurrieron
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            if (fieldError.getDefaultMessage() != null) {
                errors.put(
                        fieldError.getField(),
                        String.format("Field '%s': %s", fieldError.getField(), fieldError.getDefaultMessage())
                );
            }
        }

        //3. Definir la instancia
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                LocalDate.now()
        );

        //4. Devolver
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //1. DataNotFound
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(DataNotFoundException ex){
           ErrorResponse error = new ErrorResponse(
                   HttpStatus.NOT_FOUND.value(),
                   ex.getMessage(),
                   LocalDate.now()

           );
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //2.DataDuplicated
    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ErrorResponse>handleDuplicated(DuplicatedDataException ex){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDate.now()

        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }





}
