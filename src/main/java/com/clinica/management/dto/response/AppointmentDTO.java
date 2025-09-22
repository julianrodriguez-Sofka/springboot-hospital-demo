package com.clinica.management.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private LocalDateTime date;
    private Long doctorId;
    private Long patientId;
    private String description;
    private Boolean wasAttended;
}
