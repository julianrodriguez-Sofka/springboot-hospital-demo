package com.clinica.management.mapper;

import com.clinica.management.dto.request.CreateDoctorDTO;
import com.clinica.management.dto.request.UpdateDoctorDTO;
import com.clinica.management.dto.response.DoctorDTO;
import com.clinica.management.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Doctor toEntity(CreateDoctorDTO dto);

    DoctorDTO toDTO(Doctor doctor);

    void updateEntity(@MappingTarget Doctor doctor, UpdateDoctorDTO dto);
}
