package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService empployeeService;

	@Autowired
	public EmployeeController(EmployeeService empployeeService) {
		this.empployeeService = empployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		theModel.addAttribute("employees", empployeeService.findAllByOrderByLastNameDesc());

		return "list-employees";
	}

	@GetMapping("/newEmployeeForm")
	public String showNewForm(Model model){
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("model") Employee employee){
		System.out.println(employee);
		empployeeService.save(employee);
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
		Employee employee = empployeeService.findById(id);
		model.addAttribute("employee",employee);
		return "employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id){
		empployeeService.deleteById(id);
		return "redirect:/employees/list";
	}

}









