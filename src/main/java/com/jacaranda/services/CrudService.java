package com.jacaranda.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Film;
import com.jacaranda.entity.Rental;
import com.jacaranda.repository.CategoryRepository;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.repository.FilmRepository;
import com.jacaranda.repository.RentalRepository;

import javassist.NotFoundException;

@Service
public class CrudService {

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
	public ResponseEntity<?> getAllCustomer() {
		Iterable<Customer> resul = customerRepository.findAll();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers");
		} else {
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getAllCustomerOrdered() {
		Iterable<Customer> resul = customerRepository.findAllOrderedByName();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers");
		} else {
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getCustomerByName(String name) {
		List<Customer> customers = customerRepository.findCustomerByFullName(name);
		ResponseEntity<?> response;
		if (customers.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no customers which name is " + name);
		} else {
			response = ResponseEntity.ok(customers);
		}
		return response;
	}

	public ResponseEntity<?> addCustomer(Customer sent) {
		String dniSent = sent.getDni();
		Customer c = customerRepository.findCustomerByDni(dniSent);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.ok(customerRepository.save(sent));
		} else {
			response = ResponseEntity.badRequest().body("Customer already exists");
		}
		return response;
	}

	// SOLO PARA PROBAR
	// Al acabar borrar y descomentar el siguente
	public Customer updateCustomer(int id, Customer sent) throws NotFoundException {
		Customer c = customerRepository.findCustomerById(id);
		if (c == null) {
			throw new NotFoundException("Client doesn't exist");
		}
		updateService.updateCustomer(c, sent);
		return c;
	}

//	public ResponseEntity<?> updateCustomer(int id, Customer sent) {
//		Customer c = customerRepository.findCustomerById(id);
//		ResponseEntity<?> response;
//		if (c == null) {
//			response = ResponseEntity.notFound().build();
//		} else {
//			updateService.updateCustomer(c, sent);
//			response = ResponseEntity.ok(customerRepository.save(c));
//		}
//		return response;
//	}

	public ResponseEntity<?> deleteCustomer(int id) {
		Customer c = customerRepository.findCustomerById(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.notFound().build();
		} else {
			customerRepository.deleteById(id);
			response = ResponseEntity.ok("Customer deleted");
		}
		return response;
	}

	/*
	 * MÉTODOS PARA CATEGORY
	 */
	public ResponseEntity<?> getAllCategory() {
		Iterable<Category> resul = categoryRepository.findAll();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no categories");
		} else {
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getCategoryById(int id) {
		Category c = categoryRepository.findCategoryById(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category doesn't exist");
		} else {
			response = ResponseEntity.ok(c);
		}
		return response;
	}

	public ResponseEntity<?> addCategory(Category sent) {
		Category c = categoryRepository.findCategoryByType(sent.getType());
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.ok(categoryRepository.save(sent));
		} else {
			response = ResponseEntity.badRequest().body("Category already exists");
		}
		return response;
	}

	public ResponseEntity<?> deleteCategory(int id) {
		Category c = categoryRepository.findCategoryById(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category doesn't exist");
		} else {
			categoryRepository.deleteById(id);
			response = ResponseEntity.ok("Category deleted");
		}
		return response;
	}

	/*
	 * MÉTODOS PARA FILM
	 */
	public ResponseEntity<?> getAllFilms() {
		Iterable<Film> resul = filmRepository.findAll();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no movies");
		} else {
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getFilmsOrdered(String param) {
		Iterable<Film> resul = filmRepository.findAll();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no movies");
		} else {
			if (param.equalsIgnoreCase("originalTitle")) {
				resul = filmRepository.findAllOrderedByOriginalTitle();
			} else if (param.equalsIgnoreCase("year")) {
				resul = filmRepository.findAllOrderedByYear();
			}
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getFilmById(int id) {
		Film f = filmRepository.findFilmById(id);
		ResponseEntity<?> response;
		if (f == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie doesn't exist");
		} else {
			response = ResponseEntity.ok(f);
		}
		return response;
	}

	public ResponseEntity<?> addFilm(Film sent) {
		String titleSent = sent.getOriginalTitle();
		Film f = filmRepository.findFilmByOriginalTitle(titleSent);
		ResponseEntity<?> response;
		if (f == null) {
			int idCategory = sent.getCategory().getId();
			Category catSent = categoryRepository.findCategoryById(idCategory);
			if (catSent == null) {
				response = ResponseEntity.badRequest().body("The category of the film doesn't exist");
			} else {
				sent.setCategory(catSent);
				response = ResponseEntity.ok(filmRepository.save(sent));
			}
		} else {
			response = ResponseEntity.badRequest().body("Film already exists");
		}
		return response;
	}

	public ResponseEntity<?> updateFilm(int id, Film sent) {
		Film f = filmRepository.findFilmById(id);
		ResponseEntity<?> response;
		if (f == null) {
			response = ResponseEntity.notFound().build();
		} else {
			updateService.updateFilm(f, sent);
			response = ResponseEntity.ok(filmRepository.save(f));
		}
		return response;
	}

	public ResponseEntity<?> deleteFilm(int id) {
		Film f = filmRepository.findFilmById(id);
		ResponseEntity<?> response;
		if (f == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film doesn't exist");
		} else {
			filmRepository.deleteById(id);
			response = ResponseEntity.ok("Film deleted");
		}
		return response;
	}

	/*
	 * MÉTODOS PARA RENTAL
	 */

	public ResponseEntity<?> getAllRental() {
		Iterable<Rental> resul = rentalRepository.findAll();
		ResponseEntity<?> response;
		if (((Collection<?>) resul).size() == 0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no rentals");
		} else {
			response = ResponseEntity.ok(resul);
		}
		return response;
	}

	public ResponseEntity<?> getRentalById(int id) {
		Rental r = rentalRepository.findRentalById(id);
		ResponseEntity<?> response;
		if (r == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental doesn't exist");
		} else {
			response = ResponseEntity.ok(r);
		}
		return response;
	}

	public ResponseEntity<?> getRentalByCustomerId(int id) {
		Customer customerSent = customerRepository.findCustomerById(id);
		ResponseEntity<?> response;
		if (customerSent == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exist");
		} else {
			List<Rental> rentals = rentalRepository.findRentalByCustomer(customerSent);
			if (rentals == null) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("There are no rentals for the customer with id " + customerSent.getId());
			} else {
				response = ResponseEntity.ok(rentals);
			}
		}
		return response;
	}

	public ResponseEntity<?> addRental(Rental sent) {
		String dateSent = sent.getStartDate();
		int idClientSent = sent.getCustomer().getId();
		Rental r = queryService.findRentalByDateAndCustomer(dateSent, idClientSent);
		ResponseEntity<?> response;
		if (r == null) {
			int idCustomer = sent.getCustomer().getId();
			Customer customerSent = customerRepository.findCustomerById(idCustomer);
			if (customerSent == null) {
				response = ResponseEntity.badRequest().body("The customer of the rental doesn't exist");
			} else {
				sent.setCustomer(customerSent);
				List<Film> filmsComplete = new ArrayList<Film>();
				List<Film> films = sent.getFilms();
				for (Film film : films) {
					int idFilm = film.getId();
					Film filmComplete = filmRepository.findFilmById(idFilm);
					filmsComplete.add(filmComplete);
				}
				sent.setFilms(filmsComplete);
				films = sent.getFilms();
				for (Film film : films) {
					List<Rental> rentals = film.getRentals();
					rentals.add(sent);
					film.setRentals(rentals);
					film.setRented(true);
				}
				response = ResponseEntity.ok(rentalRepository.save(sent));
			}
		} else {
			response = ResponseEntity.badRequest().body("Rental already exists");
		}
		return response;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

}