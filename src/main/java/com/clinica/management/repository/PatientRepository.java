package com.clinica.management.repository;

import com.clinica.management.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByDni(String dni);

}
