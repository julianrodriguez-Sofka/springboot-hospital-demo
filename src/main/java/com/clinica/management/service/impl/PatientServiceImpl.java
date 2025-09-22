package com.clinica.management.service.impl;

import com.clinica.management.dto.request.UpdatePatientDTO;
import com.clinica.management.entity.Patient;
import com.clinica.management.service.DoctorService;
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

    private PatientServiceImpl(PatientRepository patientRepository,
                               PatientMapper mapper) {

        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientDTO save(CreatePatientDTO createPatientDTO) {
        Patient p = mapper.toEntity(createPatientDTO);
        Patient patientSaved = patientRepository.save(p);
        PatientDTO patientDTOSaved = mapper.toDTO(patientSaved);
        return patientDTOSaved;
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

    @Override
    public PatientDTO update(UpdatePatientDTO updatePatientDTO) {
        Patient patient = patientRepository.findById(updatePatientDTO.getId()).get();
        mapper.updateEntity(patient, updatePatientDTO);
        return mapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public void delete(Long id) {
        if (patientRepository.findById(id).isEmpty()) {
            System.out.println("Patient with id " + id + " does not exist." );

        }

        patientRepository.deleteById(id);

    }

}
