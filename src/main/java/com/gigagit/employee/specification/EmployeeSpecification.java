package com.gigagit.employee.specification;

import java.util.Arrays;

import com.gigagit.employee.model.Employee;
import com.gigagit.employee.model.Gender;

import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> firstname(String firstname) {
        if (firstname == null || firstname.isEmpty()) {
            return (root, query, cb) -> {
                return cb.conjunction();
            };
        }

        return (root, query, cb) -> {
            return cb.equal(root.get("firstname"), firstname);
        };
    }

    public static Specification<Employee> lastname(String lastname) {
        if (lastname == null || lastname.isEmpty()) {
            return (root, query, cb) -> {
                return cb.conjunction();
            };
        }

        return (root, query, cb) -> {
            return cb.equal(root.get("lastname"), lastname);
        };
    }

    public static Specification<Employee> gender(String gender) {
        boolean mGender = Arrays.stream(Gender.values()).anyMatch(g -> g.toString().equals(gender));

        if (mGender) {
            return (root, query, cb) -> {
                return cb.equal(root.get("gender"), Gender.valueOf(gender));
            };
        } else {
            return (root, query, cb) -> {
                return cb.conjunction();
            };
        }
    }

}
