package com.clinica.management.service;

import com.clinica.management.dto.request.CreateAppointmentDTO;
import com.clinica.management.dto.request.UpdateAppointmentDTO;
import com.clinica.management.dto.response.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO save(CreateAppointmentDTO dto);
    List<AppointmentDTO> getAll();
    AppointmentDTO getById(Long id);
    AppointmentDTO update(UpdateAppointmentDTO dto);
    void delete(Long id);


}
