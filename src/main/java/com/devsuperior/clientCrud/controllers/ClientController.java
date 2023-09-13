package com.devsuperior.clientCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.clientCrud.dtos.ClientDto;
import com.devsuperior.clientCrud.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(clientService.findAll(pageable));
		
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(clientService.findById(id));
	}
	
	
}