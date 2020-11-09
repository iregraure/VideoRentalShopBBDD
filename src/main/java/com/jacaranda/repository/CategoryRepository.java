package com.jacaranda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.enums.CategoryType;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	// Get de una category por id
	public Category findCategoryById(int id);
	
	// Get de una category por type
	public Category findCategoryByType(CategoryType type);

}
