package com.security.Security.Security.repository;

import com.security.Security.Security.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Integer>
{
    Optional<Employee> findByEmail(String email);
}
