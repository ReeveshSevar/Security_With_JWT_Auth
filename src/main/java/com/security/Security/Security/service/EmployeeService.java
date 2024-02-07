package com.security.Security.Security.service;

import com.security.Security.Security.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    void delete(Integer id);
    Employee getEmp(Integer id);
    List<Employee> getAll();
}
