package com.jendrix.udemy.facuracion.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;
import com.jendrix.udemy.facuracion.api.model.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegions();

}
