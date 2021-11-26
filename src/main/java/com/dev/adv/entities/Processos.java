package com.dev.adv.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Processos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@JsonIgnore
	@JoinColumn(name="info_Id", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Informacoes informacoes;
	
	@NotNull(message= "A descrição não pode ser nula")
	private String descricao;
	
	@NotNull(message= "O resultado do processo não pode ser nulo")
	private int resultado;
	
	@Column(name = "info_Id")
	private Long infoId; 
	
}
