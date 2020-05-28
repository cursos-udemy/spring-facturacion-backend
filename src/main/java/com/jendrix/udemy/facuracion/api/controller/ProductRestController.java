package com.jendrix.udemy.facuracion.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jendrix.udemy.facuracion.api.model.entity.Item;
import com.jendrix.udemy.facuracion.api.service.ItemService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

	@Autowired
	private ItemService itemService;

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/filter/{text}")
	public Iterable<Item> filterByName(@PathVariable String text) {
		return this.itemService.findByNameContainingIgnoreCase(text);
	}
}
