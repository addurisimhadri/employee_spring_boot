package com.sim.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sim.employee.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
