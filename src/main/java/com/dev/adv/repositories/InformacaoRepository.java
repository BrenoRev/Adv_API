package com.dev.adv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.adv.entities.Informacoes;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacoes, Long>{

	@Query(value = "select count(*) from informacoes where advogado_id = ?1 LIMIT 1", nativeQuery = true)
	Long exist(Long id);

}
