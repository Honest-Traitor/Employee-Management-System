package com.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.springboot.model.Employee;
import com.project.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind form data
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to database
		employeeService.saveEmployee(employee);
	
		return "redirect:/";
		
	}
	
	@GetMapping("/showUpdateEmployeeForm/{id}")
	public String showUpdateEmployeeForm(@PathVariable (value = "id") long id, Model model) {
		//get employee for the service
		Employee employee= employeeService.getEmployeeById(id);
		
		//set employee as model attribute to pre-populate form
		model.addAttribute("employee",employee);
		return "update_employee";
		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id, Model model) {
		//call delete method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";

	}
	
}
