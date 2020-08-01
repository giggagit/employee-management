package com.gigagit.employee.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gigagit.employee.model.Employee;
import com.gigagit.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	@Override
	public Page<Employee> search(Specification<Employee> spec, Pageable pageable) {
		return employeeRepository.findAll(spec, pageable);
	}

	@Override
	public Optional<Employee> findByid(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		employeeRepository.deleteById(id);
	}

}
