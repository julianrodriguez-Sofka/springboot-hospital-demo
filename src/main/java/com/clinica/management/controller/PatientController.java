package com.clinica.management.controller;

import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.request.UpdatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Pacientes", description = "Operaciones relacionadas con pacientes")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los pacientes")

    public ResponseEntity<List<PatientDTO>> getPatients(){
        return ResponseEntity.ok(patientService.getAll());
    }

    @PostMapping // /patients
    public ResponseEntity<PatientDTO> savePatient(@RequestBody CreatePatientDTO createPatientDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(createPatientDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody UpdatePatientDTO patientDTO){
        return ResponseEntity.ok(patientService.update(patientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
