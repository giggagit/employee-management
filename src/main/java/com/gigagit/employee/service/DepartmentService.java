package com.gigagit.employee.service;

import java.util.List;
import java.util.Optional;

import com.gigagit.employee.model.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

	public void save(Department department);

	public Page<Department> findByName(Pageable pageable, String name);

	public Page<Department> findAll(Pageable pageable);

	public List<Department> findAll();

	public Optional<Department> findByid(long id);

	public void deleteById(long id);

}
