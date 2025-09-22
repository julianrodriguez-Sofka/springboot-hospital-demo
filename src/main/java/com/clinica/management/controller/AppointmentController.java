package com.clinica.management.controller;

import com.clinica.management.dto.request.CreateAppointmentDTO;
import com.clinica.management.dto.response.AppointmentDTO;
import com.clinica.management.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Citas", description = "Operaciones relacionadas con citas")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        return ResponseEntity.ok(appointmentService.save(createAppointmentDTO));
    }

}
