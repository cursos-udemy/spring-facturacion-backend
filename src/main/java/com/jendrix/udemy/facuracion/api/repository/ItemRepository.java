package com.jendrix.udemy.facuracion.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jendrix.udemy.facuracion.api.model.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	
	@Query("select i from Item i where i.name like %?1%")
	public Iterable<Item> filterByName(String text);

	public Iterable<Item> findByNameContainingIgnoreCase(String text);

	public Iterable<Item> findByNameStartingWithIgnoreCase(String text);
}
