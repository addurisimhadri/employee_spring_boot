package com.sim.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sim.employee.entities.Employee;
import com.sim.employee.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/employees")public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	@GetMapping(value="/")	
	public Iterable<Employee> getEmployee(){		
		return employeeService.getEmployees();
	}
	@GetMapping(path = { "/employee/{id}" })
	public Employee getEmployee(@PathVariable("id") int id) {
		Employee employee=employeeService.getEmployeeById(id);
		return employee;
	}
	@DeleteMapping(path = { "/employee/{id}" })
	public Employee delete(@PathVariable("id") int id) {
		Employee employee=employeeService.getEmployeeById(id);
		employeeService.delete(id);
		return employee;
	}
	@PostMapping(path = { "/create" })
	public Employee create(@RequestBody Employee employee) {
		employeeService.save(employee);
		log.info(""+employee);
		return employee;
	}

}
