package com.dev.adv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.adv.entities.Processos;

@Repository
public interface ProcessoRepository extends JpaRepository<Processos, Long>{

	@Query(value = "select  * from processos left join informacoes on informacoes.id = processos.info_id where informacoes.advogado_id = ?1", nativeQuery = true)
	List<Processos> getProcessos(Long id);
	
}
