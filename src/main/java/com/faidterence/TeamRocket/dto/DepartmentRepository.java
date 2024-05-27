package com.faidterence.TeamRocket.dto;

import com.faidterence.TeamRocket.schemas.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository  extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentName(String departmentName);
    Department findById(long id);

}
