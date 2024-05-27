package com.faidterence.TeamRocket.services;

import com.faidterence.TeamRocket.repository.DepartmentRepository;
import com.faidterence.TeamRocket.dto.EmployeeDTO;
import com.faidterence.TeamRocket.repository.EmployeeRepository;
import com.faidterence.TeamRocket.schemas.Department;
import com.faidterence.TeamRocket.schemas.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Employee employee = new Employee();
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(Employee.Gender.valueOf(employeeDTO.getGender()));
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setJobTitle(employeeDTO.getJobTitle());

        // Fetch and set Department by ID
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        employee.setDepartment(department);

        // Fetch and set Manager by ID
        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID"));
            employee.setManager(manager);
        }

        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
        employee.setCity(employeeDTO.getCity());
        employee.setState(employeeDTO.getState());
        employee.setZipCode(employeeDTO.getZipCode());
        employee.setCountry(employeeDTO.getCountry());
        employee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        employee.setEmergencyContactPhone(employeeDTO.getEmergencyContactPhone());
        employee.setStatus(Employee.Status.valueOf(employeeDTO.getStatus()));

        // Set created and updated timestamps
        LocalDateTime now = LocalDateTime.now();
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);

        employee = employeeRepository.save(employee);

        // Prepare the response DTO
        EmployeeDTO createdEmployeeDTO = new EmployeeDTO();
        createdEmployeeDTO.setId(employee.getId());
        createdEmployeeDTO.setFirstName(employee.getFirstName());
        createdEmployeeDTO.setLastName(employee.getLastName());
        createdEmployeeDTO.setDateOfBirth(employee.getDateOfBirth());
        createdEmployeeDTO.setGender(employee.getGender().toString());
        createdEmployeeDTO.setEmail(employee.getEmail());
        createdEmployeeDTO.setPhoneNumber(employee.getPhoneNumber());
        createdEmployeeDTO.setHireDate(employee.getHireDate());
        createdEmployeeDTO.setJobTitle(employee.getJobTitle());
        createdEmployeeDTO.setDepartmentId(employee.getDepartment().getId());
        createdEmployeeDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
        if (employee.getManager() != null) {
            createdEmployeeDTO.setManagerId(employee.getManager().getId());
            createdEmployeeDTO.setManagerName(employee.getManager().getFirstName() + " " + employee.getManager().getLastName());
        }
        createdEmployeeDTO.setSalary(employee.getSalary());
        createdEmployeeDTO.setAddress(employee.getAddress());
        createdEmployeeDTO.setCity(employee.getCity());
        createdEmployeeDTO.setState(employee.getState());
        createdEmployeeDTO.setZipCode(employee.getZipCode());
        createdEmployeeDTO.setCountry(employee.getCountry());
        createdEmployeeDTO.setEmergencyContactName(employee.getEmergencyContactName());
        createdEmployeeDTO.setEmergencyContactPhone(employee.getEmergencyContactPhone());
        createdEmployeeDTO.setStatus(employee.getStatus().toString());
        createdEmployeeDTO.setCreatedAt(employee.getCreatedAt().toLocalDate());
        createdEmployeeDTO.setUpdatedAt(employee.getUpdatedAt().toLocalDate());

        return createdEmployeeDTO;
    }
}
