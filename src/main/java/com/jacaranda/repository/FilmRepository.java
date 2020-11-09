package com.jacaranda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {

	// Get de todas las peliculas ordenadas por titulo original
	@Query(value = "select * from Film order by originalTitle", nativeQuery = true)
	public List<Film> findAllOrderedByOriginalTitle();

	// Get de todas las peliculas ordenadas por año
	@Query(value = "select * from Film order by year", nativeQuery = true)
	public List<Film> findAllOrderedByYear();

	// Get de un film por id
	public Film findFilmById(int id);

	// Get de un film por originalTitle
	public Film findFilmByOriginalTitle(String title);

}
