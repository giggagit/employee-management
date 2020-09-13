package com.gigagit.employee.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.gigagit.employee.model.Department;
import com.gigagit.employee.service.DepartmentService;
import com.gigagit.employee.specification.DepartmentSpecification;

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
	public String indexPage(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam Map<String, String> reqParam, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id"));
		Specification<Department> spec = Specification.where(DepartmentSpecification.search(reqParam.get("name")));
		Page<Department> department = departmentService.findAll(spec, pageable);

		// Create query url
		Set<String> removeWord = new HashSet<>();
		removeWord.add("page");
		removeWord.add("add");
		removeWord.add("edit");
		removeWord.add("delete");
		
		reqParam.keySet().removeAll(removeWord);
		String url = "/department";
		String query = reqParam.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));

		if (!StringUtils.isEmpty(query)) {
			url += "?" + query;
		}

		model.addAttribute("url", url);
		model.addAttribute("pageContent", department);
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
		Optional<Department> oDepartment = departmentService.findById(id);

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
		Optional<Department> oDepartment = departmentService.findById(id);

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
		Optional<Department> oDepartment = departmentService.findById(id);
		if (oDepartment.isPresent()) {
			departmentService.deleteById(id);
			return "redirect:/department?delete=success";
		}

		return "redirect:/department?delete=error";
	}

}
