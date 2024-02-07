package com.security.Security.Security.serviceImpl;

import com.security.Security.Security.exception.UserNotFoundException;
import com.security.Security.Security.model.Employee;
import com.security.Security.Security.repository.EmployeeRepository;
import com.security.Security.Security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Employee create(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        Optional<Employee> opt = repository.findById(id);
        Employee emp ;
        if (opt.isPresent())
            repository.deleteById(id);
        else
            throw new UserNotFoundException("User Not Found");
    }

    @Override
    public Employee getEmp(Integer id) {
        Optional<Employee> opt = repository.findById(id);
        Employee emp ;
        if (opt.isPresent()){
            emp = opt.get();
            return emp;
        }
        else
            throw new UserNotFoundException("User Not Found");
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }
}
