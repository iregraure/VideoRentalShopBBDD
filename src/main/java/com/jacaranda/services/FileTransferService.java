package com.jacaranda.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.File;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.repository.FileRepository;

@Service
public class FileTransferService extends AbstractServiceUtils {

	// Repositorios
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FileRepository fileRepository;

	// Servicios
	@Autowired
	private FileHandlerService fhService;

	// Método para guardar una foto del cliente
	public ResponseEntity<?> addPicture(MultipartFile mpf, int id) {
		ResponseEntity<?> response;
		Customer c = customerRepository.findCustomerById(id);
		if (c == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer doesn't exist");
		}
		else {
			int fileSize = Integer.valueOf((int) mpf.getSize());
			if (fileSize > 1048576) {
				response = ResponseEntity.badRequest().body("The file's size must be less than 1Mb");
			}
			else {
				File file = new File (fhService.createBlob(mpf), mpf.getName(), mpf.getContentType(), fileSize);
				fileRepository.save(file);
				c.setPicture(file);
				response = ResponseEntity.ok().body(customerRepository.save(c));
			}
		}
		return response;
	}
	
	// Método para descargar la foto de un cliente
	public ResponseEntity<?> downloadPicture(int id) throws SQLException {
		ResponseEntity<?> response;
		Customer c = customerRepository.findCustomerById(id);
		if (c == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer doesn't exist");
		}
		else {
			File f = c.getPicture();
			if (f == null) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client doesn't have a picture");
			}
			else {
				response = ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(f.getFileType()))
						.header("Descarga imagen", "attachment; filename=\"" + f.getFileName() + "\"")
						.body(new ByteArrayResource(f.getFile().getBytes(1L, (int)f.getFile().length())));
			}
		}
		return response;
	}
	
	// Método para comprimir un fichero
	

}
