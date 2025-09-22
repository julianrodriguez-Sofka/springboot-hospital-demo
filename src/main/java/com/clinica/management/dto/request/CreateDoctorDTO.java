package com.clinica.management.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateDoctorDTO {
    private String name;
    private String speciality;
}
