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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@NotNull(message = "Login não pode ser nulo")
	@NotBlank(message = "Login não pode ser vázio")
	@Size(min= 2, max = 10 , message = "O Login deve ter entre 2 e 10 caracteres")
	private String login;
	
	@NotNull(message = "Email não pode ser nulo")
	@NotBlank(message = "Email não pode ser vázio")
	@Size(max = 50 , message = "O email deve ter menos de 50 caracteres")
	@Email
	private String email;
	
	@NotNull(message = "Senha não pode ser nulo")
	@NotBlank(message = "Senha não pode ser vázia")
	@Size(min =2, max = 8, message = "A senha deve ter entre 2 a 8 caracteres")
	private String senha;
	
	@NotNull(message = "A data de nascimento não pode ser nulo")
	@NotBlank(message = "A data de nascimento não pode ser vázia")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate nascimento;
	
	@NotNull(message = "O numero da conta bancaria não pode ser nulo")
	@NotBlank(message = "O numero da conta bancaria não pode ser vázia")
	private String contaBancaria;
	
	@NotNull(message = "A agencia não pode ser nulo")
	@NotBlank(message = "A agencia não pode ser vázia")
	@Size(max = 6, message = "A agência deve ter no maximo 6 caracteres")
	private int agencia;
	
	@NotNull(message = "O numero da conta não pode ser nulo")
	@NotBlank(message = "O numero da conta não pode ser vázio")
	@Size(max = 15, message = "O numero da conta deve ter no máximo 15 caracteres")
	private String numeroConta;
	
	@NotNull(message = "O nome do titular não pode ser nulo")
	@NotBlank(message = "O nome do titular não pode ser vázio")
	@Size(max = 30, message = "O nome do titular deve ter no máximo 30 caracteres")
	private String nomeTitular;
	
	@NotNull(message = "O CPF não pode ser nulo")
	@NotBlank(message = "O CPF não pode ser vázio")
	@CPF(message= "CPF inválido")
	private String cpf;
	
	@NotNull(message = "O RG não pode ser nulo")
	@NotBlank(message = "O RG não pode ser vázio")
	@Size(max = 15,  message = "O RG deve ter no máximo 15 caracteres")
	private String rg;
	
	@NotNull(message = "O orgão expeditor não pode ser nulo")
	@NotBlank(message = "O orgão expeditor não pode ser vázio")
	private String orgaoExpeditor;
	
	@NotNull(message = "O numero da OAB não pode ser nulo")
	@NotBlank(message = "O numero da OAB não pode ser vázio")
	@Size(max = 8, message = "O numero da OAB deve ter no máximo 8 caracteres")
	private String numeroOAB;
	
	@NotNull(message = "O endereço não pode ser nulo")
	@NotBlank(message = "O endereço não pode ser vázio")
	@Size(max = 150,  message = "O endereço deve ter no máximo 150 caracteres")
	private String endereco;
	
	@NotNull(message = "O estado não pode ser nulo")
	@NotBlank(message = "O estado não pode ser vázio")
	private String estado;
	
	@NotNull(message = "A cidade não pode ser nulo")
	@NotBlank(message = "A cidade não pode ser vázio")
	private String cidade;
	
	@NotNull(message = "O numero da rua não pode ser nulo")
	@NotBlank(message = "O numero da rua não pode ser vázio")
	@Size(max = 5,  message = "O numero da rua deve ter no máximo 5 caracteres")
	private int numero;
	
	
	
}
