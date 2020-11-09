package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Category;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
	
	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All category
	@GetMapping(path = "/category")
	public ResponseEntity<?> getCategories() {
		return crudService.getAllCategory();
	}

	// Category by Id
	@GetMapping(path = "/category/{id}")
	public ResponseEntity<?> getCategory(@PathVariable int id) {
		return crudService.getCategoryById(id);
	}

	// POST
	@PostMapping(path = "/category")
	public ResponseEntity<?> addCategory(@RequestBody Category sent) {
		return crudService.addCategory(sent);
	}

	// DELETE
	@DeleteMapping(path = "/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
		return crudService.deleteCategory(id);
	}

}
