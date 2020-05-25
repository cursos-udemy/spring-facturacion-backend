package com.jendrix.udemy.facuracion.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.jendrix.udemy.facuracion.api.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	//@Query("select u from User where u.username = ?1")
	public User findByUsername(String username);

}
