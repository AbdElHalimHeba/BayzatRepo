package com.bayzdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bayzdelivery.repositories.UserRepository;
import com.bayzdelivery.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository repository;
	
//	TODO HIGH - Implement user authentication logic
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		return repository.existsByUsername(username);
	}

}
