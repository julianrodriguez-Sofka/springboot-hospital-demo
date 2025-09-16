package com.clinica.management.repository;

import com.clinica.management.entity.Doctor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
