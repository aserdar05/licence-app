package com.serdar.licenceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LicenceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenceAppApplication.class, args);
	}

}
