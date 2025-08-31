package com.EmployeeRating.ServiceImplementation;

import com.EmployeeRating.Dto.LoginRequest;
import com.EmployeeRating.Dto.LoginResponse;
import com.EmployeeRating.Entity.Employee;
import com.EmployeeRating.Entity.User;
import com.EmployeeRating.Exception.InvalidCredentialsException;
import com.EmployeeRating.Exception.UserNotFoundException;
import com.EmployeeRating.Repository.EmployeeRepo;
import com.EmployeeRating.Repository.UserRepository;
import com.EmployeeRating.Service.AuthService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
       // Employee employee = employeeRepo.findByEmployeeId(user.getEmployeeId())
        //		.orElseThrow(()-> new RuntimeException("User Does not have email Id"));
        //log.info("Emp.................{}",employee );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        
        Employee employee = employeeRepo.findByEmployeeId(user.getEmployeeId())
        		.orElse(null);
        log.info("Emp.................{}",employee );

        // Redirect only for Developer and Team Lead
        String redirectUrl = null; // default no redirect
        String email=null;
        switch (user.getEmployeeRole()) {
            case "Developer":
                redirectUrl = "/Nutan-dashboard";
                break;
            case "Team Lead":
                redirectUrl = "/Anwesha-dashboard";
                email=employee.getEmployeeEmail();
                break;
            // Any other role → no redirect
            default:
                redirectUrl = null;
                break;
        }
        return new LoginResponse(
                "Login successful",
                user.getEmployeeRole(),
                user.getEmployeeId(),
                user.getEmployeeName(),
                redirectUrl,
                email
               
        );
    }
}

