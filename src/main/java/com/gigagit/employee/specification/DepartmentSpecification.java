package com.gigagit.employee.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.gigagit.employee.model.Department;

public class DepartmentSpecification {

	public static Specification<Department> search(String name) {
		List<Predicate> predicates = new ArrayList<>();

		return (root, query, cb) -> {
			if (!StringUtils.isEmpty(name)) {
				predicates.add(cb.equal(root.get("name"), name));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
