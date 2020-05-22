package com.jendrix.udemy.facuracion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
