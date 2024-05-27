package com.faidterence.TeamRocket.services;

import com.faidterence.TeamRocket.dto.DepartmentRepository;
import com.faidterence.TeamRocket.dto.EmployeeDTO;
import com.faidterence.TeamRocket.repository.EmployeeRepository;
import com.faidterence.TeamRocket.schemas.Department;
import com.faidterence.TeamRocket.schemas.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
   private  final DepartmentRepository departmentRepository;

    public Employee createEmployee(EmployeeDTO employeeDTO) {
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
        Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID"));
        employee.setManager(manager);

        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
        employee.setCity(employeeDTO.getCity());
        employee.setState(employeeDTO.getState());
        employee.setZipCode(employeeDTO.getZipCode());
        employee.setCountry(employeeDTO.getCountry());
        employee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        employee.setEmergencyContactPhone(employeeDTO.getEmergencyContactPhone());
        employee.setStatus(Employee.Status.valueOf(employeeDTO.getStatus()));

        return employeeRepository.save(employee);
    }
}
