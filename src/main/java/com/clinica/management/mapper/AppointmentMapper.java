package com.clinica.management.mapper;

import com.clinica.management.dto.request.CreateAppointmentDTO;
import com.clinica.management.dto.request.UpdateAppointmentDTO;
import com.clinica.management.dto.response.AppointmentDTO;
import com.clinica.management.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "wasAttended", ignore = true)
    Appointment toEntity(CreateAppointmentDTO dto);

    @Mapping(target = "doctorId",
            expression = "java(appointment.getDoctor() == null ? null : appointment.getDoctor().getId())")
    @Mapping(target = "patientId",
            expression = "java(appointment.getPatient() == null ? null : appointment.getPatient().getId())")
    AppointmentDTO toDTO(Appointment appointment);


    void updateEntity(@MappingTarget Appointment appointment, UpdateAppointmentDTO dto);
}
