package com.security.Security.Security.controller;

import com.security.Security.Security.exception.UserNotFoundException;
import com.security.Security.Security.model.Employee;
import com.security.Security.Security.security.jwt.JWTAuthenticationRequest;
import com.security.Security.Security.security.jwt.JWTService;
import com.security.Security.Security.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Employee employee)
    {
        employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Integer id)
    {
        Employee emp = employeeService.getEmp(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(emp);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<Employee>> getAll()
    {
        List<Employee> list = employeeService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id)
    {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody JWTAuthenticationRequest request)
    {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated())
            return jwtService.generateToken(request.getUsername());
        else
            throw new UserNotFoundException("Invalid User");
    }
}
