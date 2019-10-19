package com.jendrix.udemy.facuracion.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
