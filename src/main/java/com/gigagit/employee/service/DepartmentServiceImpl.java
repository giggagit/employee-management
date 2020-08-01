package com.gigagit.employee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.gigagit.employee.model.Department;
import com.gigagit.employee.repository.DepartmentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public Page<Department> findByName(Pageable pageable, String name) {
		return departmentRepository.findByName(pageable, name);
	}

	@Override
	public Page<Department> findAll(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findByid(long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		departmentRepository.deleteById(id);
	}

}
