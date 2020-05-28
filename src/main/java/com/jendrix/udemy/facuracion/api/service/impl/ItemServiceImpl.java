package com.jendrix.udemy.facuracion.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facuracion.api.model.entity.Item;
import com.jendrix.udemy.facuracion.api.repository.ItemRepository;
import com.jendrix.udemy.facuracion.api.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Item> filterByName(String text) {
		return this.itemRepository.filterByName(text);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Item> findByNameContainingIgnoreCase(String text) {
		return this.itemRepository.findByNameContainingIgnoreCase(text);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Item> findByNameStartingWithIgnoreCase(String text) {
		return this.itemRepository.findByNameStartingWithIgnoreCase(text);
	}

	
	
}
