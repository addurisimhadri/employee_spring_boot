package com.sim.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.employee.EmployeeIdNotFoundException;
import com.sim.employee.entities.Employee;
import com.sim.employee.service.EmployeeService;
import com.sim.employee.vo.EmployeeDTO;

import lombok.extern.java.Log;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
@Log
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@GetMapping(value="/employee/getAll")	
	public Iterable<Employee> getEmployee(){		
		return employeeService.getEmployees();
	}
	@PostMapping(path = { "/employee/add" })
	public ResponseEntity<String> create(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<>("Employee Deatails are SuccessFully Added.",HttpStatus.CREATED);
	}
	@PutMapping(path = { "/employee/update/{id}" })
	public ResponseEntity<Employee> update(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id") int id ) throws EmployeeIdNotFoundException {
		Employee employee = convertObjToXXX(employeeDTO, new TypeReference<Employee>(){});
		log.info(employee+"");		
		Optional<Employee> employeeOp=employeeService.getEmployeeById(id);
		if(!employeeOp.isPresent()) 
			throw new EmployeeIdNotFoundException("Employee id is "+id+ " Not exist.");	
		employeeService.save(employee);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	@GetMapping(path = { "/employee/get/{id}" })
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id ) throws EmployeeIdNotFoundException {		
		Optional<Employee> employee=employeeService.getEmployeeById(id);
		if(!employee.isPresent()) 
			throw new EmployeeIdNotFoundException("Employee id is "+id+ " Not exist.");	
		
		return new ResponseEntity<>(employee.get(),HttpStatus.OK);		
	}
	@DeleteMapping(path = { "/employee/delete/{id}" })
	public ResponseEntity<String> delete(@PathVariable("id") int id) throws EmployeeIdNotFoundException {		
		Optional<Employee> employee=employeeService.getEmployeeById(id);
		if(!employee.isPresent()) 
			throw new EmployeeIdNotFoundException("Employee id is "+id+ " Not exist.");	
		employeeService.delete(id);
		return new ResponseEntity<>("Successfully Deleted.",HttpStatus.OK);
	}
	
	public  Employee convertObjToXXX(Object o, TypeReference<Employee> ref) {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.convertValue(o, ref);
	}
}
