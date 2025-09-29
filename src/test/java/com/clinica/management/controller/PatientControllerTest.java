package com.clinica.management.controller;

import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    private PatientController patientController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();

        objectMapper = new ObjectMapper();
    }
    /* Pasos de prueba:
        - 1. Datos de entrada (si es necesario, datos de salida opcionales también)
        - 2. Establecer todos los comportamientos simulados necesarios
        - 3. Llamar al método a probar
        - 4. Verificar los resultados
        - 5. Verificar todas las interacciones con los mocks
        */

    @Test
    void getPatients_shouldReturnContent() throws Exception{
        //1
        List<PatientDTO> patients = new ArrayList<>();
        patients.add(new PatientDTO(1L,"123456","Mishell", "mishell@mail.com",29));
        patients.add(new PatientDTO(2L, "654321","Erick", "erick@mail.com",28));

        //2
        Mockito.when(patientService.getAll()).thenReturn(patients);

        //3 y 4
        mockMvc.perform(get("/patients/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        //5
        Mockito.verify(patientService).getAll();


    }

    @Test
    void getPatients_shouldReturnEmpty() throws Exception {
        //1.
        List<PatientDTO> patients = new ArrayList<>();

        //2.
        Mockito.when(patientService.getAll()).thenReturn(patients);

        //3. y 4.
        mockMvc.perform(get("/patients/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        //5.
        Mockito.verify(patientService).getAll();

    }

    @Test
    void savePatient_Success() throws Exception {
        //1.
        CreatePatientDTO createPatientDTO = new CreatePatientDTO("0987456332","Mishell","mishell@mail.com", 29);
        PatientDTO patientDTO = new PatientDTO(1L, createPatientDTO.getDni(), createPatientDTO.getName(), createPatientDTO.getEmail(), createPatientDTO.getAge());

        //2.
        Mockito.when(patientService.save(Mockito.any(CreatePatientDTO.class))).thenReturn(patientDTO);

        //3. y 4.
        mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createPatientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dni").value("0987456332"));
        //5.
        Mockito.verify(patientService).save(Mockito.any(CreatePatientDTO.class));

    }

    @Test
    void getPatientById() throws Exception {
        //1.
        PatientDTO p = new PatientDTO(1L,"123456","Mishell", "mishell@mail.com",29);

        //2.
        Mockito.when(patientService.getById(Mockito.any(Long.class))).thenReturn(p);

        //3. y //4.
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("123456"));

        //5.
        Mockito.verify(patientService).getById(Mockito.any(Long.class));

    }

    @Test
    void updatePatient() {
    }

    @Test
    void deletePatient() {
    }
}