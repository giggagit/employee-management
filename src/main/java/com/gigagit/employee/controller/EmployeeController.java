package com.gigagit.employee.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gigagit.employee.model.Employee;
import com.gigagit.employee.service.DepartmentService;
import com.gigagit.employee.service.EmployeeService;
import com.gigagit.employee.specification.EmployeeSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService;

	public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
	}

	@ModelAttribute("pageNav")
	public String pageNav() {
		return "employee";
	}

	@GetMapping
	public String indexPage(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 10);
		Page<Employee> employees = employeeService.findAll(pageable);
		model.addAttribute("pageContent", employees);
		model.addAttribute("url", "/employee");
		return "pages/employee/index";
	}

	@GetMapping("/add")
	public String getAdd(Employee employee, Model model) {
		model.addAttribute("state", "add");
		model.addAttribute("departments", departmentService.findAll());
		return "pages/employee/employee-form";
	}

	@PostMapping("/add")
	public String PostAdd(@Validated Employee employee, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("state", "add");
			model.addAttribute("departments", departmentService.findAll());
			return "pages/employee/employee-form";
		}

		employeeService.save(employee);
		return "redirect:/employee?add=success";
	}

	@GetMapping("/edit/{id}")
	public String getUpdate(@PathVariable long id, Model model) {
		Optional<Employee> oEmployee = employeeService.findByid(id);

		if (oEmployee.isPresent()) {
			model.addAttribute("state", "edit");
			model.addAttribute("employee", oEmployee.get());
			model.addAttribute("departments", departmentService.findAll());
		} else {
			return "redirect:/employee/?error";
		}

		return "pages/employee/employee-form";
	}

	@PostMapping("/edit/{id}")
	public String postUpdate(@PathVariable long id, @Validated Employee employee, BindingResult bindingResult,
			Model model) {
		Optional<Employee> oEmployee = employeeService.findByid(id);

		if (!oEmployee.isPresent()) {
			return "redirect:/employee/?edit=error";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("state", "edit");
			model.addAttribute("departments", departmentService.findAll());
			return "pages/employee/employee-form";
		}

		employee.setId(id);
		employeeService.save(employee);
		return "redirect:/employee/?edit=success";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable long id) {
		Optional<Employee> oEmployee = employeeService.findByid(id);

		if (oEmployee.isPresent()) {
			employeeService.deleteById(id);
			return "redirect:/employee?delete=success";
		}

		return "redirect:/employee?delete=error";
	}

	@GetMapping("/search")
	public String search(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam Map<String, String> reqParam, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 10);

		Specification<Employee> spec = EmployeeSpecification.firstname(reqParam.get("firstname"))
				.and(EmployeeSpecification.lastname(reqParam.get("lastname"))
						.and(EmployeeSpecification.gender(reqParam.get("gender"))));

		Page<Employee> employees = employeeService.search(spec, pageable);
		reqParam.remove("page");

		String url = "/employee/search?" + reqParam.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));

		model.addAttribute("url", url);
		model.addAttribute("pageContent", employees);
		return "pages/employee/index";
	}

}
