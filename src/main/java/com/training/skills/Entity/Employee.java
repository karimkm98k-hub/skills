package com.training.skills.Entity;

import com.training.skills.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    public String name;
    public Long salary;
    public Department department;

}
