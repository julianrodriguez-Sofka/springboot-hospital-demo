package com.clinica.management.service.impl;

import com.clinica.management.service.PatientService;
import org.springframework.stereotype.Service;
import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.repository.PatientRepository;
import com.clinica.management.mapper.PatientMapper;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper mapper;

    private PatientServiceImpl(PatientRepository patientRepository, PatientMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientDTO save(CreatePatientDTO createPatientDTO) {
        return mapper.toDTO(patientRepository.save(mapper.toEntity(createPatientDTO)));
    }

    @Override
    public List<PatientDTO> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> mapper.toDTO(patient))
                .toList();
    }

    @Override
    public PatientDTO getById(Long id) {
        return mapper.toDTO(patientRepository.getById(id));
    }

}
