package com.sim.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sim.employee.entities.JWTUser;

public interface JWTUserRepository extends JpaRepository<JWTUser, Long> {

	JWTUser findByUsername(String username);

}
