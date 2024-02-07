package com.security.Security.Security.empDetails;

import com.security.Security.Security.exception.UserNotFoundException;
import com.security.Security.Security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetails implements UserDetailsService
{
    @Autowired
    private EmployeeRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(EmployeeDetailsService::new).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }
}
