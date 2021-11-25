package com.dev.adv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.adv.entities.Especialidades;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long>{

}
