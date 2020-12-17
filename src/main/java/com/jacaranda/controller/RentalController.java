package com.jacaranda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Film;
import com.jacaranda.entity.Rental;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class RentalController {
	
	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All rentals
	@GetMapping(path = "/rental")
	public ResponseEntity<?> getRentals() {
		ResponseEntity<?> response;
		try
		{
			List<Rental> rentals = crudService.getAllRental();
			response = ResponseEntity.ok(rentals);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// Rental by Id
	@GetMapping(path = "/rental/{id}")
	public ResponseEntity<?> getRental(@PathVariable int id) {
		ResponseEntity<?> response;
		try
		{
			Rental rental = crudService.getRentalById(id);
			response = ResponseEntity.ok(rental);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}
	
	// Rental by Customer
	@GetMapping(path = "rentals/{id}")
	public ResponseEntity<?> getRentalsCustomer(@PathVariable int id) {
		ResponseEntity<?> response;
		try
		{
			List<Rental> rentals = crudService.getRentalByCustomerId(id);
			response = ResponseEntity.ok(rentals);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// POST
	@PostMapping(path = "/rental")
	public ResponseEntity<?> addRental(@RequestBody Rental sent) {
		ResponseEntity<?> response;
		try
		{
			Rental rental = crudService.addRental(sent);
			response = ResponseEntity.ok(rental);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

}
