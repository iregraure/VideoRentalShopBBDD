package com.jacaranda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.File;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {

	// Get de un file por id
	public File findFileById(int id);

}
