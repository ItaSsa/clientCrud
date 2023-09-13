package com.devsuperior.clientCrud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clientCrud.dtos.ClientDto;
import com.devsuperior.clientCrud.entities.Client;
import com.devsuperior.clientCrud.repositories.ClientRepository;

import jakarta.validation.Valid;

@Component
public class ClientService {
	
	@Autowired 
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAll(Pageable pageable) {
		Page<Client> result = clientRepository.findAll(pageable);
		return result.map(x -> new ClientDto(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		Client client = result.get();
		return new ClientDto(client);
	}
	
	@Transactional
	public ClientDto insert (ClientDto dto) {
		Client entity = new Client(dto);
		clientRepository.save(entity);
		return new ClientDto(entity);
	}
}
