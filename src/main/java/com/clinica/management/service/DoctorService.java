package com.clinica.management.service;

import com.clinica.management.dto.request.CreateDoctorDTO;
import com.clinica.management.dto.request.UpdateDoctorDTO;
import com.clinica.management.dto.response.DoctorDTO;

import java.util.List;

public interface DoctorService {
    DoctorDTO save(CreateDoctorDTO createDoctorDTO);
    List<DoctorDTO> getAll();
    DoctorDTO getById(Long id);
    DoctorDTO update(UpdateDoctorDTO updateDoctorDTO);
    void delete(Long id);
}
