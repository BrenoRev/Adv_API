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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate startAdvogacia;
	
	// Tem que ser nulas, irão ser calculadas pelo tempo
	@Nullable 
	private Long tempoAdvogacia;
	
	@Range(min=1,max=999,message="Máximo de 3 caracteres")
	@NotNull(message= "A quantidade de clientes não pode ser nula")
	private int quantidadeClientes;
	
	// Tem que ser nulas, irão ser calculadas pelos processos ganhos
	@Nullable
	private Integer causasGanhas;
	@Nullable
	private Integer causasPerdidas;

	@Range(min=1,max=99999,message="Máximo de 5 caracteres")
	@NotNull(message= "O tempo médio de processo não pode ser nulo")
	private int tempoMedioProcesso;
	
	@Range(min=1,max=999999,message="Máximo de 6 caracteres")
	@NotNull(message= "O o valor médio por hora não pode ser nulo")
	private Double valorMedioHora;
	
	
}
