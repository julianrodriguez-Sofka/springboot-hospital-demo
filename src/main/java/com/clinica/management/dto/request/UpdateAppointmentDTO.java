package com.clinica.management.dto.request;

import lombok.Data;

@Data
public class UpdateAppointmentDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String description;
    private Boolean wasAttended;
}
