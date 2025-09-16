package com.clinica.management.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Patient {
    private Long id;
    private String dni;
    private String name;
    private String email;
    private Integer age;

}
