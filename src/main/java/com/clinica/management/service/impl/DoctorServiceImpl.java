package com.clinica.management.service.impl;

import com.clinica.management.dto.request.CreateDoctorDTO;
import com.clinica.management.dto.request.UpdateDoctorDTO;
import com.clinica.management.dto.response.DoctorDTO;
import com.clinica.management.entity.Doctor;
import com.clinica.management.mapper.DoctorMapper;
import com.clinica.management.repository.DoctorRepository;
import com.clinica.management.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper mapper;

    private DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper mapper) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    @Override
    public DoctorDTO save(CreateDoctorDTO createDoctorDTO) {
        Doctor d = mapper.toEntity(createDoctorDTO);
        Doctor savedDoctor = doctorRepository.save(d);
        DoctorDTO savedDoctorDTO = mapper.toDTO(savedDoctor);
        return savedDoctorDTO;
    }

    @Override
    public List<DoctorDTO> getAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> mapper.toDTO(doctor))
                .toList();
    }

    @Override
    public DoctorDTO getById(Long id) {
        return null;
    }

    @Override
    public DoctorDTO update(UpdateDoctorDTO updateDoctorDTO) {
        //1. Buscar el doctor por id
        //2. Si no existe, lanzar una excepción
        //3. Actualizar los campos del doctor con los datos del DTO
        //4. Si existe, Guardar el doctor actualizado en la base de datos
        //5. Convertir el doctor actualizado a DoctorDTO y devolverlo
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
