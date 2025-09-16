package com.clinica.management.repository;

import com.clinica.management.entity.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
