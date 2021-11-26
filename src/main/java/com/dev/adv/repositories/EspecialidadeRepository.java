package com.dev.adv.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.adv.entities.Especialidades;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidades, Long>{
	
	@Query(value= "SELECT id FROM especialidades WHERE area_atuacao = ?1 LIMIT 1", nativeQuery = true)
	Long getByName(String nome); 
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM especialidades WHERE id = ?1", nativeQuery = true)
	void deletarAid(Long id);
	
	@Query(value = "select * from especialidades right join informacoes on informacoes.id = especialidades.info_id where informacoes.advogado_id = ?1", nativeQuery = true)
	List<Especialidades> getEspecialidades(Long id);

}
