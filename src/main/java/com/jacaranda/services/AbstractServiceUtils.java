package com.jacaranda.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AbstractServiceUtils {
	
	protected Logger logger;
	
	@PostConstruct
	private void initLogger() {
		logger = LoggerFactory.getLogger(getClass());
	}
	
}
