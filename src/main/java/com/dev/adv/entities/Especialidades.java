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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Especialidades implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	private String areaAtuacao;

	@JsonIgnore
	@JoinColumn(name="info_Id", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Informacoes informacoes;
	 
	@Column(name = "info_Id")
	private Long infoId; 
	
}
