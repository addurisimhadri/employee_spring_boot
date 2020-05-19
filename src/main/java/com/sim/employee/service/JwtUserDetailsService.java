package com.sim.employee.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sim.employee.entities.JWTUser;
import com.sim.employee.repo.JWTUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private JWTUserRepository jwtUserRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JWTUser user = jwtUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public JWTUser save(com.sim.employee.vo.JWTUser user) {
		JWTUser jwtUser = new JWTUser();
		jwtUser.setUsername(user.getUsername());
		jwtUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return jwtUserRepository.save(jwtUser);
	}
}