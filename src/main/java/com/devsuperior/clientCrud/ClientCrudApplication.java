package com.devsuperior.clientCrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClientCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("READY!");
		
	}

}
