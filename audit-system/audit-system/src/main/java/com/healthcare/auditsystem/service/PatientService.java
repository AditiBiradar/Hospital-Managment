package com.healthcare.auditsystem.service;

import com.healthcare.auditsystem.entity.Patient;
import com.healthcare.auditsystem.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }
    public List<Patient> findAll() { return repository.findAll(); }
    public Patient findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Patient save(Patient p) { return repository.save(p); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
