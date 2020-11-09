package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jacaranda.entity.Film;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class FilmController {

	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All films
	@GetMapping(path = "/film")
	public ResponseEntity<?> getFilms() {
		return crudService.getAllFilms();
	}

	// All films sorted
	@GetMapping(path = "/filmOrdered")
	public ResponseEntity<?> getFilmsOrdered(@RequestParam(required = false) String param) {
		return crudService.getFilmsOrdered(param);
	}

	// Film by Id
	@GetMapping(path = "/film/{id}")
	public ResponseEntity<?> getFilm(@PathVariable int id) {
		return crudService.getFilmById(id);
	}

	// POST
	@PostMapping(path = "/film")
	public ResponseEntity<?> addFilm(@RequestBody Film sent) {
		return crudService.addFilm(sent);
	}

	// PUT
	@PutMapping(path = "/film/{id}")
	public ResponseEntity<?> updateFilm(@PathVariable int id, @RequestBody Film sent) {
		return crudService.updateFilm(id, sent);
	}

	// DELETE
	@DeleteMapping(path = "/film/{id}")
	public ResponseEntity<?> deleteFilm(@PathVariable int id) {
		return crudService.deleteFilm(id);
	}

}
