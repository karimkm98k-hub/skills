package com.training.skills.Services;

import com.training.skills.Department;
import com.training.skills.Entity.Employee;
import com.training.skills.Exception.ResourceNotFoundException;
import com.training.skills.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {

    @Test
    void findByDepartmentReturnsOnlyEmployeesInRequestedDepartment() {
        Employee itEmployee = new Employee("Alex", 18000L, Department.IT);
        Employee hrEmployee = new Employee("Sara", 16000L, Department.HR);
        EmployeeService service = new EmployeeService(new TestEmployeeRepository(List.of(itEmployee, hrEmployee)));

        List<Employee> employees = service.findByDepartment(Department.IT);

        assertEquals(1, employees.size());
        assertEquals("Alex", employees.get(0).getName());
        assertEquals(Department.IT, employees.get(0).getDepartment());
    }

    @Test
    void findByDepartmentThrowsWhenNoEmployeesExistForDepartment() {
        Employee hrEmployee = new Employee("Sara", 16000L, Department.HR);
        EmployeeService service = new EmployeeService(new TestEmployeeRepository(List.of(hrEmployee)));

        assertThrows(ResourceNotFoundException.class, () -> service.findByDepartment(Department.IT));
    }

    private static class TestEmployeeRepository implements EmployeeRepository {
        private final List<Employee> employees;

        private TestEmployeeRepository(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public List<Employee> findAll() {
            return employees;
        }

        @Override
        public List<Employee> findByGreaterSalary(double salary) {
            return employees.stream()
                    .filter(employee -> employee.getSalary() > salary)
                    .toList();
        }

        @Override
        public List<Employee> findByDepartment(Department department) {
            return employees.stream()
                    .filter(employee -> employee.getDepartment() == department)
                    .toList();
        }
    }
}
