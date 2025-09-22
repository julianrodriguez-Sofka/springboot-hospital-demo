package com.clinica.management.mapper;
import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.request.UpdatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" )
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target ="doctor", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Patient toEntity(CreatePatientDTO dto);

    PatientDTO toDTO(Patient patient);

    void updateEntity(@MappingTarget Patient patient, UpdatePatientDTO dto);

}
