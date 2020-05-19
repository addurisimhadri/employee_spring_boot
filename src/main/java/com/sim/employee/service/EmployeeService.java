package com.sim.employee.service;

import com.sim.employee.entities.Employee;

public interface EmployeeService {
	
	Iterable<Employee> getEmployees();
	Employee save(Employee employee);
	void delete(int id);
	Employee getEmployeeById(int id);

}
