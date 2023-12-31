package com.devsuperior.clientCrud.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.clientCrud.dtos.ClientDto;
import com.devsuperior.clientCrud.services.ClientService;

import jakarta.validation.Valid;

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
	
	@PostMapping
	public ResponseEntity<ClientDto> insert(@Valid @RequestBody  ClientDto dto) {
		dto = clientService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	
	}
	
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<ClientDto> update(@PathVariable Long id,
												@Valid 	@RequestBody  ClientDto dto) {
		return ResponseEntity.ok( clientService.update(id,dto));
		
	}
	
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<ClientDto> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
}
