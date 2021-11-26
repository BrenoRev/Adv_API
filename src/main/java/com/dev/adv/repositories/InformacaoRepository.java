package com.dev.adv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.adv.entities.Informacoes;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacoes, Long>{
	

}
