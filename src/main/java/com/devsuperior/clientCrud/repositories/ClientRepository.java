package com.devsuperior.clientCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.clientCrud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
