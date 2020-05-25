package com.jendrix.udemy.facuracion.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facuracion.api.model.entity.User;
import com.jendrix.udemy.facuracion.api.repository.UserRepository;
import com.jendrix.udemy.facuracion.api.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			logger.error(String.format("User '%s' not found", username));
			throw new UsernameNotFoundException(String.format("User '%s' not found", username));
		}

		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info(String.format("username: %s, role: %s", username, authority.getAuthority())))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

}
