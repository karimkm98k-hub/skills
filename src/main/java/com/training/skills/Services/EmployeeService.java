package com.training.skills.Services;

import com.training.skills.Department;
import com.training.skills.Entity.Employee;
import com.training.skills.Entity.Post;
import com.training.skills.Exception.ResourceNotFoundException;
import com.training.skills.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void postLike(Long postId) {

        Post post = employeeRepository.findById(postId).get();

        post.setLikes(post.getLikes() + 1);

        employeeRepository.save(post);


    }

    @Autowired
    private final EmployeeRepository employeeRepository;

    public List<Employee> findByAvgSalary(double salary) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getSalary() > 15000)
                .toList();
    }

    public List<Employee> findByAverageSalary(double salary) {
        List<Employee> employees = employeeRepository.findByGreaterSalary(salary);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found.");
        }

        return employees;
    }

    public List<String> findEmpByNames() {
        return employeeRepository.findAll()
                .stream()
                .map(Employee::getName)
                .toList();
    }

    public List<Employee> findByDepartment(Department department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found.");
        }

        return employees;
    }

    public double findTotalSalaryForIT() {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == Department.IT)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}
