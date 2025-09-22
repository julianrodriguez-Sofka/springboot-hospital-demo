package com.clinica.management.dto.request;

import lombok.Data;

@Data
public class CreateAppointmentDTO {
    private Long doctorId;
    private Long patientId;
    private String description;
}
