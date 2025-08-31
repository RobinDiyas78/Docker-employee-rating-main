package com.EmployeeRating.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamLeadEmployeeDto {
    private String employeeId;
    private String designation;
    private String employeeName;
    private String projectName;
} 