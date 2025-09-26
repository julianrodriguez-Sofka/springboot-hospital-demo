package com.clinica.management.service.impl;

import com.clinica.management.dto.request.CreatePatientDTO;
import com.clinica.management.dto.response.PatientDTO;
import com.clinica.management.entity.Doctor;
import com.clinica.management.entity.Patient;
import com.clinica.management.exception.DataNotFoundException;
import com.clinica.management.exception.DuplicatedDataException;
import com.clinica.management.mapper.PatientMapper;
import com.clinica.management.repository.PatientRepository;
import com.clinica.management.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper mapper;

    private PatientService patientService;

    private Patient patientTest;

    @BeforeEach
    void setUp() {
        patientService = new PatientServiceImpl(patientRepository, mapper);
        patientTest = new Patient(1L,"987456123","Mishell","mishell@mail.com", 29, new Doctor(), new ArrayList<>());
    }

    /* Pasos de prueba:
        - 1. Datos de entrada (si es necesario, datos de salida opcionales también)
        - 2. Establecer todos los comportamientos simulados necesarios
        - 3. Llamar al método a probar
        - 4. Verificar los resultados
        - 5. Verificar todas las interacciones con los mocks
        */

    @Test
    void save_successful() {
        //1.
        Patient pTest2 = new Patient(null, patientTest.getDni(),patientTest.getName(), patientTest.getEmail(), patientTest.getAge(), null, null);

        //2.
        Mockito.when(mapper.toEntity(Mockito.any(CreatePatientDTO.class))).thenReturn(pTest2);
        Mockito.when(patientRepository.existsByDni(Mockito.any(String.class))).thenReturn(false);
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenAnswer(invocation -> {
            Patient p = invocation.getArgument(0);
            p.setId(1L);
            return p;

        });
        Mockito.when(mapper.toDTO(Mockito.any(Patient.class))).thenAnswer(invocation -> {
            Patient p = invocation.getArgument(0);
            return new PatientDTO(
                    p.getId(),
                    p.getDni(),
                    p.getName(),
                    p.getEmail(),
                    p.getAge()
            );
        });

        //3.
        var result = patientService.save(new CreatePatientDTO(patientTest.getDni(),
                patientTest.getName(),
                patientTest.getEmail(),
                patientTest.getAge()));

        //4.
        assertAll("Patient saved",
                () -> assertInstanceOf(PatientDTO.class, result),
                () -> assertEquals(1L, result.getId())

        );

        //5.
        Mockito.verify(mapper).toEntity(Mockito.any(CreatePatientDTO.class));
        Mockito.verify(patientRepository).existsByDni(Mockito.any(String.class));
        Mockito.verify(patientRepository).save(Mockito.any(Patient.class));
        Mockito.verify(mapper).toDTO(Mockito.any(Patient.class));


    }

    @Test
    void save_failed(){
        //2.
        Mockito.when(patientRepository.existsByDni(Mockito.any(String.class))).thenReturn(true);

        //3.
        var result = assertThrows(DuplicatedDataException.class, () -> patientService.save(new CreatePatientDTO(patientTest.getDni(),
                patientTest.getName(),
                patientTest.getEmail(),
                patientTest.getAge())));


        //4.
        assertInstanceOf(DuplicatedDataException.class, result);

        //5.
        Mockito.verify(patientRepository).existsByDni(Mockito.any(String.class));
        Mockito.verifyNoMoreInteractions(mapper);



    }

    @Test
    void getAll_Success_WithResults() {

        //2.
        Mockito.when(patientRepository.findAll()).thenReturn(List.of(patientTest, patientTest));

        Mockito.when(mapper.toDTO(any(Patient.class))).thenAnswer(invocation -> {
            Patient p = invocation.getArgument(0);
            return new PatientDTO(
                    p.getId(),
                    p.getDni(),
                    p.getName(),
                    p.getEmail(),
                    p.getAge()
            );
        });

        //3.
        var result = patientService.getAll();

        //4.
        assertEquals(2, result.size());
        assertNotNull(result);

        //5.
        Mockito.verify(patientRepository).findAll();
        Mockito.verify(mapper, Mockito.times(2)).toDTO(any(Patient.class));



    }

    @Test
    void getAll_Failed_NoResults(){

        //2.
        Mockito.when(patientRepository.findAll()).thenReturn(new ArrayList<>());

        //3.
        var result = patientService.getAll();

        //4.
        assertEquals(0, result.size());

        //5.
        Mockito.verify(patientRepository).findAll();
        Mockito.verifyNoMoreInteractions(mapper);
    }

    @Test
    void getById_Success() {
        //1.
        PatientDTO pDTO = new PatientDTO(1L,"987456123","Mishell","mishell@mail.com",29);

        //2.
        Mockito.when(patientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(patientTest));
        Mockito.when(mapper.toDTO(any(Patient.class))).thenAnswer(invocation -> {
            Patient p = invocation.getArgument(0);
            return new PatientDTO(
                    p.getId(),
                    p.getDni(),
                    p.getName(),
                    p.getEmail(),
                    p.getAge()
            );
        });

        //3.
        var result = patientService.getById(1L);

        //4.
        assertAll("User found",
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals(pDTO.getDni(), result.getDni()
                ));

        //5.
        Mockito.verify(patientRepository).findById(any(Long.class));
        Mockito.verify(mapper).toDTO(any(Patient.class));
    }

    @Test
    void getById_Failed_ThrowsExceptions(){
        //2.
        Mockito.when(patientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        //3.
        var result = assertThrows(DataNotFoundException.class, () -> patientService.getById(1L));


        //4.
        assertInstanceOf(DataNotFoundException.class, result);

        //5.
        Mockito.verify(patientRepository).findById(Mockito.any(Long.class));
        Mockito.verifyNoMoreInteractions(mapper);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}