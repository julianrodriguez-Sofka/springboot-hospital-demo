package com.clinica.management.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorDTO {
    private Long id;
    private String name;
    private String speciality;
}
