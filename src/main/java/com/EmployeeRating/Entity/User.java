package com.EmployeeRating.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;   // 👈 EmpId from Excel (for login)

    @Column(nullable = false)
    private String password; // Default Rumango@123

    @Column(nullable = false)
    private String employeeRole;     // Developer / TeamLeader

    private String employeeName; // 👈 Employee Name from Excel
    
}

