package com.clinica.management.mapper;
import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface PatientMapper {

    Patient toEntity(CreatePatientDTO dto);
    PatientDTO toDTO(Patient patient);

}
