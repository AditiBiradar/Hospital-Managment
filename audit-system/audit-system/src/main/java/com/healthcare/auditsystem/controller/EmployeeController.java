package com.healthcare.auditsystem.controller;

import com.healthcare.auditsystem.annotation.Auditable;
import com.healthcare.auditsystem.entity.Employee;
import com.healthcare.auditsystem.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    @Auditable(action = "LIST", resourceType = "Employee")
    public List<Employee> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @Auditable(action = "VIEW", resourceType = "Employee")
    public Employee getOne(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @Auditable(action = "CREATE", resourceType = "Employee")
    public Employee create(@RequestBody Employee employee) { return service.save(employee); }

    @PutMapping("/{id}")
    @Auditable(action = "UPDATE", resourceType = "Employee")
    public Employee update(@PathVariable Long id, @RequestBody Employee updated) {
        Employee existing = service.findById(id);
        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setJobTitle(updated.getJobTitle());
        existing.setDepartment(updated.getDepartment());
        existing.setHireDate(updated.getHireDate());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    @Auditable(action = "DELETE", resourceType = "Employee")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
