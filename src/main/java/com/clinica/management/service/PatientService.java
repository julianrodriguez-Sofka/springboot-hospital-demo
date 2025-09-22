package com.clinica.management.service;
import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.request.UpdatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import java.util.List;

public interface PatientService {
    //save, update, delete, getById, getAll
    PatientDTO save(CreatePatientDTO createPatientDTO);
    List<PatientDTO> getAll();
    PatientDTO getById(Long id);
    PatientDTO  update(UpdatePatientDTO updatePatientDTO);
    void delete(Long id);



}
