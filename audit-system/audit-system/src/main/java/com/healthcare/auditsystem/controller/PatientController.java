package com.healthcare.auditsystem.controller;

import com.healthcare.auditsystem.annotation.Auditable;
import com.healthcare.auditsystem.entity.Patient;
import com.healthcare.auditsystem.service.PatientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @Auditable(action = "VIEW", resourceType = "Patient")
    public Patient getOne(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @Auditable(action = "CREATE", resourceType = "Patient")
    public Patient create(@RequestBody Patient patient) { return service.save(patient); }

    @PutMapping("/{id}")
    @Auditable(action = "UPDATE", resourceType = "Patient")
    public Patient update(@PathVariable Long id, @RequestBody Patient updated) {
        Patient existing = service.findById(id);
        existing.setFullName(updated.getFullName());
        existing.setDiagnosis(updated.getDiagnosis());
        existing.setAssignedDoctor(updated.getAssignedDoctor());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    @Auditable(action = "DELETE", resourceType = "Patient")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
