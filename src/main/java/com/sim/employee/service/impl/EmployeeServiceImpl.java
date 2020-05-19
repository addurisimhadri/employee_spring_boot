package com.sim.employee.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sim.employee.entities.Employee;
import com.sim.employee.repo.EmployeeRepository;
import com.sim.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public Iterable<Employee> getEmployees() {
		Iterable<Employee> employees=employeeRepository.findAll();;
		return employees;
	}
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);		
	}
	@Override
	public Optional<Employee> getEmployeeById(int id) {		
		return employeeRepository.findById(id);
	}

}
