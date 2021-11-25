package com.dev.adv.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Especialidades implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Id
	private Long id;
	
	@ManyToOne
	private Informacoes informacoes;
	
	@NotBlank(message = "A área de atuação não pode ser vázia")
	@NotNull(message = "A área de atuação não poder ser nula")
	private String areaAtuacao;
}
