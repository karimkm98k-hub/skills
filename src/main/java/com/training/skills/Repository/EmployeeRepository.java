package com.training.skills.Repository;

import com.training.skills.Entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    List<Employee> findAll();

    List<Employee> findByGreaterSalary(double salary);
}
