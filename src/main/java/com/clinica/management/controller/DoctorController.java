package com.clinica.management.controller;

import com.clinica.management.dto.request.CreateDoctorDTO;
import com.clinica.management.dto.request.UpdateDoctorDTO;
import com.clinica.management.dto.response.DoctorDTO;
import com.clinica.management.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctores", description = "Operaciones relacionadas con doctores")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getDoctors(){
        return ResponseEntity.ok(doctorService.getAll());
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody CreateDoctorDTO createDoctorDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(createDoctorDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody UpdateDoctorDTO updateDoctorDTO){
        return ResponseEntity.ok(doctorService.update(updateDoctorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
