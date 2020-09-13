package com.gigagit.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigagit.employee.model.Department;
import com.gigagit.employee.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	@Transactional
	public void save(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public Page<Department> findAll(Specification<Department> spec, Pageable pageable) {
		return departmentRepository.findAll(spec, pageable);
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findById(long id) {
		return departmentRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		// departmentRepository.setNullOnDelete(id);
		departmentRepository.deleteById(id);
	}

}
