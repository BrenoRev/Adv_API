package com.dev.adv.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Processos implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToOne(mappedBy = "processos")
	private Informacoes informacoes;
	
	@NotBlank
	@NotNull
	private String descricao;
	
	@NotBlank
	@NotNull
	private int resultado;
}
