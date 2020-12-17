package com.jacaranda.services;

import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import com.jacaranda.entity.Customer;
import com.jacaranda.repository.CustomerRepository;

import javassist.NotFoundException;

class UpdateCustomerTest {
	
	// Subject Under Test
	private CrudService sut;
	
	// Dependencies
	private CustomerRepository mockedCustomerRepo;
	private UpdateService mockedUpdateService;
	private Customer mockedCustomer;
	
	@BeforeEach
	private void init()
	{
		sut = new CrudService();
		// mocking dependencies
		mockedCustomerRepo = mock(CustomerRepository.class);
		mockedUpdateService = mock(UpdateService.class);
		mockedCustomer = mock(Customer.class);
		// set dependencies into sut
		sut.setCustomerRepository(mockedCustomerRepo);
		sut.setUpdateService(mockedUpdateService);
	}

	@Test
	public void updateCustomerThrowsNotFound() {
		try
		{
			// Behavior
			Mockito.when(mockedCustomerRepo.findCustomerById(Mockito.anyInt())).thenReturn(null);
			// Call
			sut.updateCustomer(2, mockedCustomer);
		}
		catch(NotFoundException nfe)
		{
			// Check
			Assert.isInstanceOf(NotFoundException.class, nfe);
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
	@Test
	public void updateCustomerReturnsCustomer() 
	{
		try
		{
			// Behavior
			Mockito.when(mockedCustomerRepo.findCustomerById(Mockito.anyInt())).thenReturn(mockedCustomer);
			// Call
			assert(mockedCustomer == sut.updateCustomer(2, mockedCustomer));
		}
		catch (Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}

}

/*
	public Customer updateCustomer(int id, Customer sent) throws NotFoundException {
		Customer c = customerRepository.findCustomerById(id);
		if (c == null) {
			throw new NotFoundException("Client doesn't exist");
		}
		updateService.updateCustomer(c, sent);
		return c;
	}
 */