package com.gigagit.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gigagit.employee.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

	// @Modifying
	// @Query("update Employee emp set emp.department.id = null where emp.department.id = ?1")
	// public void setNullOnDelete(long id);
	
}
