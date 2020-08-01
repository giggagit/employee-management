package com.gigagit.employee.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gigagit.employee.model.Department;
import com.gigagit.employee.service.DepartmentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@ModelAttribute("pageNav")
	public String pageNav() {
		return "department";
	}

	@GetMapping
	public String indexPage(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 10);
		Page<Department> departments = departmentService.findAll(pageable);

		model.addAttribute("pageContent", departments);
		model.addAttribute("url", "/department");
		return "pages/department/index";
	}

	@GetMapping("/add")
	public String getAdd(Department department, Model model) {
		model.addAttribute("state", "add");
		return "pages/department/department-form";
	}

	@PostMapping("/add")
	public String PostAdd(@Validated Department department, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("state", "add");
			return "pages/department/department-form";
		}

		departmentService.save(department);
		return "redirect:/department?add=success";
	}

	@GetMapping("/edit/{id}")
	public String getUpdate(@PathVariable long id, Model model) {
		Optional<Department> oDepartment = departmentService.findByid(id);

		if (!oDepartment.isPresent()) {
			return "redirect:/department?error";
		}

		model.addAttribute("state", "edit");
		model.addAttribute("department", oDepartment.get());
		return "pages/department/department-form";
	}

	@PostMapping("/edit/{id}")
	public String postUpdate(@PathVariable long id, @Validated Department department, BindingResult bindingResult,
			Model model) {
		Optional<Department> oDepartment = departmentService.findByid(id);

		if (!oDepartment.isPresent()) {
			return "redirect:/department?edit=error";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("state", "edit");
			return "pages/department/department-form";
		}

		oDepartment.get().setName(department.getName());
		departmentService.save(oDepartment.get());
		return "redirect:/department?edit=success";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Optional<Department> oDepartment = departmentService.findByid(id);

		if (oDepartment.isPresent()) {
			departmentService.deleteById(id);
			return "redirect:/department?delete=success";
		}

		return "redirect:/department?delete=error";
	}

	@GetMapping("/search")
	public String search(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam Map<String, String> reqParam, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 10);
		Page<Department> employees;

		if (reqParam.get("name").isBlank()) {
			employees = departmentService.findAll(pageable);
		} else {
			employees = departmentService.findByName(pageable, reqParam.get("name"));
		}

		reqParam.remove("page");

		String url = "/department/search?" + reqParam.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));

		model.addAttribute("url", url);
		model.addAttribute("pageContent", employees);
		return "pages/department/index";
	}

}
