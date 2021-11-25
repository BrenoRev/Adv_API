package com.dev.adv.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Advogado advogado; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "processos_id", referencedColumnName = "id")
	private Processos processos;
	
	@NotBlank(message = "As especialidades não podem ser vázias")
	@NotNull(message = "As especialidades não podem ser nulas")
	@OneToMany(mappedBy="informacoes", orphanRemoval = false, cascade = CascadeType.ALL)
	private List<Especialidades> especialidades = new ArrayList<>();
	
	@NotBlank(message = "A data não pode ser vázia")
	@NotNull(message = "A data não pode ser nula")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate tempoAdvogacia;
	
	@NotBlank(message = "A quantidade de clientes não pode ser vázia")
	@NotNull(message = "A quantidade de clientes não pode ser nula")
	private int quantidadeClientes;
	
	@NotBlank(message = "A quantidade de causas ganhas não pode ser vázio")
	@NotNull(message = "A quantidade de causas ganhas não pode ser nula")
	private int causasGanhas;
	
	@NotBlank(message = "A quantidade de causas perdidas não pode ser vázia")
	@NotNull(message = "A quantidade de causas perdidas não pode ser nula")
	private int causasPerdidas;

	@NotBlank(message = "A quantidade de tempo médio por processo não pode ser vázio")
	@NotNull(message = "A quantidade de tempo médio por processo não pode ser nula")
	@Size(max = 5)
	private int tempoMedioProcesso;
	
	@NotBlank(message = "O valor médio por hora não pode ser vázia")
	@NotNull(message = "O valor médio por hora não pode ser nula")
	@Size(max = 6)
	private Double valorMedioHora;
}
