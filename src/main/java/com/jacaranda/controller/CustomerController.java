package com.jacaranda.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jacaranda.entity.Customer;
import com.jacaranda.services.CrudService;
import com.jacaranda.services.FileTransferService;

@RestController
@RequestMapping(path = "/api")
public class CustomerController
{

	// Servicios
	@Autowired
	private CrudService crudService;

	@Autowired
	private FileTransferService ftService;

	// GET
	// All customers
	@GetMapping(path = "/customer")
	public ResponseEntity<?> getCustomers()
	{
		ResponseEntity<?> response;
		try
		{
			List<Customer> customers = crudService.getAllCustomer();
			response = ResponseEntity.ok(customers);
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers");
		}
		return response;
	}

	// All customers sorted
	@GetMapping(path = "/customerOrdered")
	public ResponseEntity<?> getCustomersOrdered()
	{
		ResponseEntity<?> response;
		try
		{
			List<Customer> customers = crudService.getAllCustomerOrdered();
			response = ResponseEntity.ok(customers);
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers");
		}
		return response;
	}

	// Customer by Name
	@GetMapping(path = "/customer/{name}")
	public ResponseEntity<?> getCustomer(@PathVariable String name)
	{
		ResponseEntity<?> response;
		try
		{
			List<Customer> customers = crudService.getCustomerByName(name);
			response = ResponseEntity.ok(customers);
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers with the name " + name);
		}
		return response;
	}

	// Download picture
	@GetMapping(path = "/customer/{id}/picture")
	public ResponseEntity<?> downloadPicture(@PathVariable int id) throws SQLException
	{
		return ftService.downloadPicture(id);
	}

	// POST
	@PostMapping(path = "/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer sent)
	{
		ResponseEntity<?> response;
		try
		{
			Customer customer = crudService.addCustomer(sent);
			response = ResponseEntity.ok(customer);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// PUT
	// Actualizar datos del cliente
	@PutMapping(path = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer sent)
	{
		ResponseEntity<?> response;
		try
		{
			Customer customer = crudService.updateCustomer(id, sent);
			response = ResponseEntity.ok(customer);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// Añadir una foto
	@PutMapping(path = "/customer/{id}/picture")
	public ResponseEntity<?> addPicture(@PathVariable int id, @RequestParam MultipartFile mpf) throws IOException
	{
		return ftService.addPicture(mpf, id);
	}

	// DELETE
	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id)
	{
		ResponseEntity<?> response;
		try
		{
			Customer customer = crudService.deleteCustomer(id);
			response = ResponseEntity.ok(customer);
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers with the id " + id);
		}
		return response;
	}

}
