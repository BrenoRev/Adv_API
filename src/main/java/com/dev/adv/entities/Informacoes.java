package com.dev.adv.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Informacoes implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToOne
	private Advogado advogado; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "processos_id", referencedColumnName = "id")
	private Processos processos;
	
	@NotBlank
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate tempoAdvogacia;
	
	@NotBlank
	@NotNull
	private int quantidadeClientes;
	
	@NotBlank
	@NotNull
	private int causasGanhas;
	
	@NotBlank
	@NotNull
	private int causasPerdidas;
	
	@NotBlank
	@NotNull
	@OneToMany(fetch = FetchType.EAGER)
	private List<Especialidades> especialidades;
	 
}
