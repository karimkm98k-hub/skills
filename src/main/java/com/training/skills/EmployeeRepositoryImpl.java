package com.training.skills;

import com.training.skills.Entity.Employee;
import com.training.skills.Repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public List<Employee> findByGreaterSalary(double salary) {
        return List.of();
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        return findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList();
    }
}
