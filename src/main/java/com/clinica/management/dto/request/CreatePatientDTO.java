package com.clinica.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePatientDTO {
    private String dni;
    private String name;
    private String email;
    private Integer age;
}
