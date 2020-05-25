package com.jendrix.udemy.facuracion.api.service;

import com.jendrix.udemy.facuracion.api.model.entity.User;

public interface UserService {

	public User findByUsername(String username);

}
