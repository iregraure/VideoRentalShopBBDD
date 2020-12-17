package com.jacaranda.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.jacaranda.entity.Customer;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.services.CrudService;
import com.jacaranda.services.FileTransferService;

@MockBean(value = {FileTransferService.class})
@ContextConfiguration(classes=CustomerController.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerMockTest
{

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerRepository repo;
	
	@MockBean
	private CrudService service;
	
	private final static String PATH = "/api/customer";
	
	@Test
	public void getCustomers() throws Exception
	{
		Mockito.when(service.getAllCustomer())
				.thenThrow(new Exception("Customer doesn't exist"))
				.thenReturn(new ArrayList<Customer>());
		mockMvc.perform(get(PATH)).andExpect(status().isNotFound());
		mockMvc.perform(get(PATH)).andExpect(status().isOk());
	}

}
