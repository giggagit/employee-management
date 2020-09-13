package com.gigagit.employee.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.gigagit.employee.model.Employee;
import com.gigagit.employee.model.Gender;

public class EmployeeSpecification {

	public static Specification<Employee> search(String firstname, String lastname, String gender) {
		boolean mGender = Arrays.stream(Gender.values()).anyMatch(g -> g.toString().equals(gender));
		List<Predicate> predicates = new ArrayList<>();

		return (root, query, cb) -> {
			if (!StringUtils.isEmpty(firstname)) {
				predicates.add(cb.equal(root.get("firstname"), firstname));
			}

			if (!StringUtils.isEmpty(lastname)) {
				predicates.add(cb.equal(root.get("lastname"), lastname));
			}

			if (mGender) {
				predicates.add(cb.equal(root.get("gender"), Gender.valueOf(gender)));
			}

			// When paging, it will call a count query, but the count query doesn't allow fetch
			// When result type is long, it means the count query executed
			if (query.getResultType() != Long.class && query.getResultType() != long.class) {
				root.fetch("department", JoinType.LEFT);
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
