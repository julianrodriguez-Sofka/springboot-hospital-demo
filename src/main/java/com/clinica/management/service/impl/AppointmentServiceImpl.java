package com.clinica.management.service.impl;

import com.clinica.management.dto.request.CreateAppointmentDTO;
import com.clinica.management.dto.request.UpdateAppointmentDTO;
import com.clinica.management.dto.response.AppointmentDTO;

import com.clinica.management.entity.Appointment;
import com.clinica.management.entity.Doctor;
import com.clinica.management.entity.Patient;
import com.clinica.management.mapper.AppointmentMapper;
import com.clinica.management.repository.AppointmentRepository;
import com.clinica.management.repository.DoctorRepository;
import com.clinica.management.repository.PatientRepository;
import com.clinica.management.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper mapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentMapper mapper,
                                  DoctorRepository doctorRepository,
                                  PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.mapper = mapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;

    }

    @Override
    public AppointmentDTO save(CreateAppointmentDTO dto) {
        /*doctorId, patientId, description*/
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).get();
        Patient patient = patientRepository.findById(dto.getPatientId()).get();

        //Assign the doctor to the patient
        patient.setDoctor(doctor);
        patientRepository.save(patient);

        // Create the appointment
        Appointment appToSave = mapper.toEntity(dto);
        appToSave.setDoctor(doctor);
        appToSave.setPatient(patient);
        appToSave.setDate(LocalDateTime.now());
        appToSave.setWasAttended(false);

        return mapper.toDTO(appointmentRepository.save(appToSave));
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(app -> mapper.toDTO(app))
                .toList();
    }

    @Override
    public AppointmentDTO getById(Long id) {
        return null;
    }

    @Override
    public AppointmentDTO update(UpdateAppointmentDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
