package com.jacaranda.videoRentalShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.jacaranda.repository"})
@SpringBootApplication(scanBasePackages = {"com.jacaranda.controller", "com.jacaranda.services"})
@EntityScan(basePackages = {"com.jacaranda.entity"})
public class VideoRentalShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalShopApplication.class, args);
	}

}
