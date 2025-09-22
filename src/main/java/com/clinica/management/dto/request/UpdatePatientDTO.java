package com.clinica.management.dto.request;

import lombok.Data;

@Data
public class UpdatePatientDTO {
    private Long id;
    private String dni;
    private String name;
    private String email;
    private Integer age;
}
