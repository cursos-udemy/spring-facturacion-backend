package com.jendrix.udemy.facuracion.api.service;

import com.jendrix.udemy.facuracion.api.model.entity.Item;

public interface ItemService {

	public Iterable<Item> filterByName(String text);

	public Iterable<Item> findByNameContainingIgnoreCase(String text);

	public Iterable<Item> findByNameStartingWithIgnoreCase(String text);

}
