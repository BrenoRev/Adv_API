package com.dev.adv.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Advogado implements Serializable{

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToOne
	private Informacoes informacoes;
	
	@NotNull
	@NotBlank
	@Size(min= 2, max = 10)
	private String login;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@Size(min =1, max = 8)
	private String senha;
	
	@NotNull
	@NotBlank
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate nascimento;
	
	@NotNull
	@NotBlank
	private String contaBancaria;
	
	@NotNull
	@NotBlank
	@Size(max = 6)
	private int agencia;
	
	@NotNull
	@NotBlank
	@Size(max = 15)
	private String numeroConta;
	
	@NotNull
	@NotBlank
	@Size(max = 30)
	private String nomeTitular;
	
	@NotNull
	@NotBlank
	@CPF(message= "CPF inválido")
	private String cpf;
	
	@NotNull
	@NotBlank
	@Size(max = 15)
	private String rg;
	
	@NotNull
	@NotBlank
	private String orgaoExpeditor;
	
	@NotNull
	@NotBlank
	@Size(max = 8)
	private String numeroOAB;
	
	@NotNull
	@NotBlank
	@Size(max = 150)
	private String endereco;
	
	@NotNull
	@NotBlank
	private String estado;
	
	@NotNull
	@NotBlank
	private String cidade;
	
	@NotNull
	@NotBlank
	@Size(max = 5)
	private int numero;
	
	
	
}
