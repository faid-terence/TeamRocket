package com.faidterence.TeamRocket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;
    private Long departmentId;
    private String departmentName;
    private Long managerId;
    private String managerName;
    private Double salary;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
