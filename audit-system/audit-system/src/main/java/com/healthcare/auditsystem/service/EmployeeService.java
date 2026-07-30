package com.healthcare.auditsystem.service;

import com.healthcare.auditsystem.entity.Employee;
import com.healthcare.auditsystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    public List<Employee> findAll() { return repository.findAll(); }
    public Employee findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Employee save(Employee e) { return repository.save(e); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
