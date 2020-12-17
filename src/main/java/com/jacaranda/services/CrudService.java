package com.jacaranda.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Film;
import com.jacaranda.entity.Rental;
import com.jacaranda.repository.CategoryRepository;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.repository.FilmRepository;
import com.jacaranda.repository.RentalRepository;

@Service
public class CrudService
{

	// Repositorios
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private RentalRepository rentalRepository;

	// Servicios
	@Autowired
	private UpdateService updateService;

	@Autowired
	private QueryService queryService;

	/*
	 * MÉTODOS PARA CUSTOMER
	 */
	public List<Customer> getAllCustomer() throws Exception
	{
		List<Customer> resul = (List<Customer>) customerRepository.findAll();
		if (resul.size() == 0)
		{
			throw new Exception("There are no customers");
		}
		return resul;
	}

	public List<Customer> getAllCustomerOrdered() throws Exception
	{
		List<Customer> resul = (List<Customer>) customerRepository.findAllOrderedByName();
		if (resul.size() == 0)
		{
			throw new Exception("There are no customers");
		}
		return resul;
	}

	public List<Customer> getCustomerByName(String name) throws Exception
	{
		List<Customer> customers= customerRepository.findCustomerByFullName(name);
		if (customers.size() == 0)
		{
			throw new Exception("Customer doesn't exist");
		}
		return customers;
	}

	public Customer addCustomer(Customer sent) throws Exception
	{
		String dniSent = sent.getDni();
		Customer customer = customerRepository.findCustomerByDni(dniSent);
		if (customer != null)
		{
			throw new Exception("Customer already exists");
		}
		customerRepository.save(customer);
		return customer;
	}

	public Customer updateCustomer(int id, Customer sent) throws Exception
	{
		Customer customer = customerRepository.findCustomerById(id);
		if (customer == null)
		{
			throw new Exception("Client doesn't exist");
		}
		updateService.updateCustomer(customer, sent);
		customerRepository.save(customer);
		return customer;
	}

	public Customer deleteCustomer(int id) throws Exception
	{
		Customer c = customerRepository.findCustomerById(id);
		if (c == null)
		{
			throw new Exception("Customer doesn't exist");
		}
		customerRepository.delete(c);
		return c;
	}

	/*
	 * MÉTODOS PARA CATEGORY
	 */
	public List<Category> getAllCategory() throws Exception
	{
		List<Category> resul = (List<Category>) categoryRepository.findAll();
		if (resul.size() == 0)
		{
			throw new Exception("There are no categories");
		}
		return resul;
	}

	public Category getCategoryById(int id) throws Exception
	{
		Category c = categoryRepository.findCategoryById(id);
		if (c == null)
		{
			throw new Exception("Category doesn't exist");
		}
		return c;
	}

	public Category addCategory(Category sent) throws Exception
	{
		Category c = categoryRepository.findCategoryByType(sent.getType());
		if (c != null)
		{
			throw new Exception("Category already exists");
		}
		categoryRepository.save(sent);
		return c;
	}

	public Category deleteCategory(int id) throws Exception
	{
		Category c = categoryRepository.findCategoryById(id);
		if (c == null)
		{
			throw new Exception("Category doesn't exist");
		}
		categoryRepository.deleteById(id);
		return c;
	}

	/*
	 * MÉTODOS PARA FILM
	 */
	public List<Film> getAllFilms() throws Exception
	{
		List<Film> resul = (List<Film>) filmRepository.findAll();
		if (resul.size() == 0)
		{
			throw new Exception("There are no movies");
		}
		return resul;
	}

	public List<Film> getFilmsOrdered(String param) throws Exception
	{
		List<Film> resul = (List<Film>) filmRepository.findAll();
		if (resul.size() == 0)
		{
			throw new Exception("There are no movies");
		}
		if (param.equalsIgnoreCase("originalTitle"))
		{
			resul = filmRepository.findAllOrderedByOriginalTitle();
		}
		else if (param.equalsIgnoreCase("year"))
		{
			resul = filmRepository.findAllOrderedByYear();
		}
		return resul;
	}

	public Film getFilmById(int id) throws Exception
	{
		Film f = filmRepository.findFilmById(id);
		if (f == null)
		{
			throw new Exception("Movie doesn't exist");
		}
		return f;
	}

	public Film addFilm(Film sent) throws Exception
	{
		String titleSent = sent.getOriginalTitle();
		Film f = filmRepository.findFilmByOriginalTitle(titleSent);
		if (f != null)
		{
			throw new Exception("Film already exists");
		}
		int idCategory = sent.getCategory().getId();
		Category catSent = categoryRepository.findCategoryById(idCategory);
		if (catSent == null)
		{
			throw new Exception("The category of the film doesn't exist");
		}
		sent.setCategory(catSent);
		filmRepository.save(sent);
		return sent;
	}

	public Film updateFilm(int id, Film sent) throws Exception
	{
		Film f = filmRepository.findFilmById(id);
		if (f == null)
		{
			throw new Exception("Film doesn't exist");
		}
		updateService.updateFilm(f, sent);
		filmRepository.save(f);

		return f;
	}

	public Film deleteFilm(int id) throws Exception
	{
		Film f = filmRepository.findFilmById(id);
		if (f == null)
		{
			throw new Exception("Film doesn't exist");
		}
		filmRepository.deleteById(id);
		return f;
	}

	/*
	 * MÉTODOS PARA RENTAL
	 */

	public List<Rental> getAllRental() throws Exception
	{
		List<Rental> resul = (List<Rental>) rentalRepository.findAll();
		if (resul.size() == 0)
		{
			throw new Exception("There are no rentals");
		}
		return resul;
	}

	public Rental getRentalById(int id) throws Exception
	{
		Rental r = rentalRepository.findRentalById(id);
		if (r == null)
		{
			throw new Exception("Rental doesn't exist");
		}
		return r;
	}

	public List<Rental> getRentalByCustomerId(int id) throws Exception
	{
		Customer customerSent = customerRepository.findCustomerById(id);
		if (customerSent == null)
		{
			throw new Exception("Customer doesn't exist");
		}
		List<Rental> rentals = rentalRepository.findRentalByCustomer(customerSent);
		if (rentals == null)
		{
			throw new Exception("There are no rentals for the customer with id " + customerSent.getId());
		}
		return rentals;
	}

	public Rental addRental(Rental sent) throws Exception
	{
		String dateSent = sent.getStartDate();
		int idClientSent = sent.getCustomer().getId();
		Rental r = queryService.findRentalByDateAndCustomer(dateSent, idClientSent);
		if (r != null)
		{
			throw new Exception("Rental already exists");
		}
		Customer customerSent = customerRepository.findCustomerById(idClientSent);
		if (customerSent == null)
		{
			throw new Exception("The customer of the rental doesn't exist");
		}
		sent.setCustomer(customerSent);
		List<Film> filmsComplete = new ArrayList<Film>();
		List<Film> films = sent.getFilms();
		for (Film film : films)
		{
			int idFilm = film.getId();
			Film filmComplete = filmRepository.findFilmById(idFilm);
			filmsComplete.add(filmComplete);
		}
		sent.setFilms(filmsComplete);
		films = sent.getFilms();
		for (Film film : films)
		{
			List<Rental> rentals = film.getRentals();
			rentals.add(sent);
			film.setRentals(rentals);
			film.setRented(true);
		}
		rentalRepository.save(sent);
		return sent;
	}

	public void setCustomerRepository(CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository;
	}

	public void setUpdateService(UpdateService updateService)
	{
		this.updateService = updateService;
	}

}