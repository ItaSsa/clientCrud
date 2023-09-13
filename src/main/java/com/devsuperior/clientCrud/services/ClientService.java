package com.devsuperior.clientCrud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clientCrud.Exceptions.ResourceNotFoundException;
import com.devsuperior.clientCrud.dtos.ClientDto;
import com.devsuperior.clientCrud.entities.Client;
import com.devsuperior.clientCrud.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

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
		Client client = result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		return new ClientDto(client);
	}
	
	@Transactional
	public ClientDto insert (ClientDto dto) {
		Client entity = new Client(dto);
		clientRepository.save(entity);
		return new ClientDto(entity);
	}
	
	
	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
		try {
			Client entity = clientRepository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			entity = clientRepository.save(entity);
			return new ClientDto(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(" Client not found ");
		}		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete (Long id) {
		
		if (!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Client not found");
		}
		
		clientRepository.deleteById(id);  
	}
	
	
	private void copyDtoToEntity(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setChildren(dto.getChildren());
		
	}

	
}
