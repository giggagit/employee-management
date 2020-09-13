package com.gigagit.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.gigagit.employee.model.Department;

public interface DepartmentService {

	public void save(Department department);

	public Page<Department> findAll(Specification<Department> spec, Pageable pageable);

	public List<Department> findAll();

	public Optional<Department> findById(long id);

	public void deleteById(long id);

}
