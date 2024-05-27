package com.faidterence.TeamRocket.controllers;


import com.faidterence.TeamRocket.dto.EmployeeDTO;
import com.faidterence.TeamRocket.schemas.Employee;
import com.faidterence.TeamRocket.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody EmployeeDTO employee) {
       return this.employeeService.createEmployee(employee);
    }
}
