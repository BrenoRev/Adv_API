package com.dev.adv.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
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
	
	@NotEmpty(message = "Login não pode ser vázio")
	@Length(min= 2, max = 10 , message = "O Login deve ter entre 2 e 10 caracteres")
	private String Login;

	@NotEmpty(message = "Email não pode ser vázio")
	@Length(max = 50 , message = "O email deve ter menos de 50 caracteres")
	@Email
	private String email;

	@NotEmpty(message = "Senha não pode ser vázia")
	@Length(min =2, max = 8, message = "A senha deve ter entre 2 a 8 caracteres")
	private String senha;

	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate nascimento;

	@NotEmpty(message = "O numero da conta bancaria não pode ser vázia")
	private String contaBancaria;
	

	@Range(min=1,max=999999,message="Máximo de 6 caracteres")
	private int agencia;

	@NotEmpty(message = "O numero da conta não pode ser vázio")
	@Length(min = 1, max = 15, message = "O numero da conta deve ter no máximo 15 caracteres")
	private String numeroConta;

	@NotEmpty(message = "O nome do titular não pode ser vázio")
	@Length(min = 1, max = 30, message = "O nome do titular deve ter no máximo 30 caracteres")
	private String nomeTitular;

	@NotEmpty(message = "O CPF não pode ser vázio")
	@CPF(message= "CPF inválido") 
	private String cpf;
	
	@NotEmpty(message = "O RG não pode ser vázio")
	@Length(min = 1, max = 15,  message = "O RG deve ter no máximo 15 caracteres")
	private String rg;
	
	@NotEmpty(message = "O orgão expeditor não pode ser vázio")
	private String orgaoExpeditor;
	
	@NotEmpty(message = "O numero da OAB não pode ser vázio")
	@Length(min = 1, max = 8, message = "O numero da OAB deve ter no máximo 8 caracteres")
	private String numeroOAB;
	
	@NotEmpty(message = "O endereço não pode ser vázio")
	@Length(min = 1, max = 150,  message = "O endereço deve ter no máximo 150 caracteres")
	private String endereco;

	@NotEmpty(message = "O estado não pode ser vázio")
	private String estado;

	@NotEmpty(message = "A cidade não pode ser vázio")
	private String cidade;
	 
	@Range(min=1,max=99999,message="Máximo de 5 caracteres")
	private int numero;

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "info_Id", insertable = false, updatable = false)
	private Informacoes informacoes;
	
	@Column(name = "info_Id")
	private Long infoId;
}
