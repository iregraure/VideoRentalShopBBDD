package com.jacaranda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Film;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class FilmController {

	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All films
	@GetMapping(path = "/film")
	public ResponseEntity<?> getFilms() {
		ResponseEntity<?> response;
		try
		{
			List<Film> films = crudService.getAllFilms();
			response = ResponseEntity.ok(films);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// All films sorted
	@GetMapping(path = "/filmOrdered")
	public ResponseEntity<?> getFilmsOrdered(@RequestParam(required = false) String param) {
		ResponseEntity<?> response;
		try
		{
			List<Film> films = crudService.getFilmsOrdered(param);
			response = ResponseEntity.ok(films);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// Film by Id
	@GetMapping(path = "/film/{id}")
	public ResponseEntity<?> getFilm(@PathVariable int id) {
		ResponseEntity<?> response;
		try
		{
			Film film = crudService.getFilmById(id);
			response = ResponseEntity.ok(film);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// POST
	@PostMapping(path = "/film")
	public ResponseEntity<?> addFilm(@RequestBody Film sent) {
		ResponseEntity<?> response;
		try
		{
			Film film = crudService.addFilm(sent);
			response = ResponseEntity.ok(film);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// PUT
	@PutMapping(path = "/film/{id}")
	public ResponseEntity<?> updateFilm(@PathVariable int id, @RequestBody Film sent) {
		ResponseEntity<?> response;
		try
		{
			Film film = crudService.updateFilm(id, sent);
			response = ResponseEntity.ok(film);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

	// DELETE
	@DeleteMapping(path = "/film/{id}")
	public ResponseEntity<?> deleteFilm(@PathVariable int id) {
		ResponseEntity<?> response;
		try
		{
			Film film = crudService.deleteFilm(id);
			response = ResponseEntity.ok(film);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}

}
