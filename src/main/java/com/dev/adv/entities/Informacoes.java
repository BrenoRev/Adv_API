package com.dev.adv.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import lombok.Data;

@Entity
@Data
public class Informacoes implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToMany(mappedBy="informacoes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Especialidades> especialidades = new ArrayList<>();
	
	@OneToMany(mappedBy="informacoes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Processos> processos = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne
	private Advogado advogado;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate tempoAdvogacia;
	
	private int quantidadeClientes;
	
	// Tem que ser nulas, irão ser calculadas pelos processos ganhos
	@Nullable
	private Integer causasGanhas;
	@Nullable
	private Integer causasPerdidas;

	private int tempoMedioProcesso;
	
	private Double valorMedioHora;
	
	
}
