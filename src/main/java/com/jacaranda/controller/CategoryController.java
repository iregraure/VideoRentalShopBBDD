package com.jacaranda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class CategoryController
{

	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All category
	@GetMapping(path = "/category")
	public ResponseEntity<?> getCategories()
	{
		ResponseEntity<?> response;
		try
		{
			List<Category> category = crudService.getAllCategory();
			response = ResponseEntity.ok(category);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// Category by Id
	@GetMapping(path = "/category/{id}")
	public ResponseEntity<?> getCategory(@PathVariable int id)
	{
		ResponseEntity<?> response;
		try
		{
			Category category = crudService.getCategoryById(id);
			response = ResponseEntity.ok(category);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// POST
	@PostMapping(path = "/category")
	public ResponseEntity<?> addCategory(@RequestBody Category sent)
	{
		ResponseEntity<?> response;
		try
		{
			Category category = crudService.addCategory(sent);
			response = ResponseEntity.ok(category);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// DELETE
	@DeleteMapping(path = "/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id)
	{
		ResponseEntity<?> response;
		try
		{
			Category category = crudService.deleteCategory(id);
			response = ResponseEntity.ok(category);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

}
