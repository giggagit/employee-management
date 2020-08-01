package com.gigagit.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigagit.employee.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Page<Department> findByName(Pageable pageable, String name);

}
