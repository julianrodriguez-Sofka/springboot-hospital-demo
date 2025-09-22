package com.clinica.management.dto.request;

import lombok.Data;

@Data
public class UpdateDoctorDTO {
    private Long id;
    private String name;
    private String speciality;
}
