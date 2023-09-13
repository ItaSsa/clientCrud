package com.devsuperior.clientCrud.dtos;

import java.time.LocalDate;

import com.devsuperior.clientCrud.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ClientDto {
	
	private Long id;
	@NotBlank(message = " Field required")
	private String name;
	@Size(min=11, message="The cpf must be 11 characters, only numbers")
	@NotBlank(message = " Field required")
	private String cpf;
	@Positive(message="The income must be greater than zero")
	private Double income;
	@Past(message = "The birthDate must be in the past")
	private LocalDate birthDate;
	@PositiveOrZero(message = "The number of children must be postive or zero")
	private Integer children;
	
	public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDto(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
	
	
	
}
