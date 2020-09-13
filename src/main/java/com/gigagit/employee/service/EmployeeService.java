package com.gigagit.employee.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.gigagit.employee.model.Employee;

public interface EmployeeService {

	public void save(Employee employee);

	public Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

	public Optional<Employee> findById(long id);

	public void deleteById(long id);

}
