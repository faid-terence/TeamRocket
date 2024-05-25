package com.faidterence.TeamRocket.repository;

import com.faidterence.TeamRocket.schemas.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);


    boolean existsByEmail(String email);
}
