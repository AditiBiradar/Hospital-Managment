package com.healthcare.auditsystem.repository;

import com.healthcare.auditsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
