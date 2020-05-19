package com.sim.employee.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sim.employee.EmployeeIdNotFoundException;
import com.sim.employee.entities.Employee;
import com.sim.employee.service.EmployeeService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	@GetMapping(value="/employee/getAll")	
	public Iterable<Employee> getEmployee(){		
		return employeeService.getEmployees();
	}
	@PostMapping(path = { "/employee/add" })
	public Employee create(@RequestBody Employee employee) {
		employeeService.save(employee);
		log.info(""+employee);
		return employee;
	}
	@PostMapping(path = { "/employee/update/{id}" })
	public ResponseEntity<Employee> update(@RequestBody Employee employee,@PathVariable("id") int id ) throws EmployeeIdNotFoundException {
		Optional<Employee> employeeOp=employeeService.getEmployeeById(id);
		if(!employeeOp.isPresent()) 
			throw new EmployeeIdNotFoundException("Employee id is "+id+ " Not exist.");	
		employeeService.save(employee);
		log.info(""+employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	@GetMapping(path = { "/employee/get/{id}" })
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id ,UriComponentsBuilder builder) throws EmployeeIdNotFoundException {		
		Optional<Employee> employee=employeeService.getEmployeeById(id);
		 HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(builder.path("/employee/get/{id}").buildAndExpand(employee.get().getId()).toUri());
		if(!employee.isPresent()) 
			throw new EmployeeIdNotFoundException("Employee id is "+id+ " Not exist.");	
		
		return new ResponseEntity<Employee>(headers,HttpStatus.OK);		
	}
	@DeleteMapping(path = { "/employee/delete/{id}" })
	public void delete(@PathVariable("id") int id) {
		employeeService.delete(id);
	}
	

}
