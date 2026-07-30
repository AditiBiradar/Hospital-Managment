package com.healthcare.auditsystem.repository;

import com.healthcare.auditsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
