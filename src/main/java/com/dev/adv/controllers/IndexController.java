package com.dev.adv.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.adv.entities.Advogado;
import com.dev.adv.entities.Especialidades;
import com.dev.adv.entities.Informacoes;
import com.dev.adv.entities.Processos;
import com.dev.adv.repositories.AdvogadoRepository;
import com.dev.adv.repositories.EspecialidadeRepository;
import com.dev.adv.repositories.InformacaoRepository;
import com.dev.adv.repositories.ProcessoRepository;

@Controller 
@RequestMapping(value = "advogados")
public class IndexController {

	@Autowired
	private AdvogadoRepository advogadoRepository;

	@Autowired
	private InformacaoRepository informacaoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Advogado>> findAll(){
		
		List<Advogado> advogados = advogadoRepository.findAll();
		for(Advogado adv: advogados) {
			List<Especialidades> esp = especialidadeRepository.getEspecialidades(adv.getId());
			adv.getInformacoes().setEspecialidades(esp);
		}

		return ResponseEntity.ok(advogados);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Advogado> findById(@PathVariable("id") Long id){
		
		Advogado advogado = advogadoRepository.findById(id).get();
		List<Especialidades> esp = especialidadeRepository.getEspecialidades(id);
		List<Processos> proc = processoRepository.getProcessos(id);
		advogado.getInformacoes().setEspecialidades(esp);
		advogado.getInformacoes().setProcessos(proc);
		return ResponseEntity.ok(advogado);
	}
	 
	@PostMapping("/adv")
	public ResponseEntity<String> register(@Valid @RequestBody Advogado advogado){
		advogadoRepository.save(advogado);		 
		return ResponseEntity.ok("Usuario " + advogado.getId() + " Salvo com sucesso!");
	}
	
	@PostMapping("/informacoes/{id}")
	public ResponseEntity<String> registerInfo(@PathVariable("id") Long id, @RequestBody Informacoes informacoes) throws InterruptedException{
		informacoes.setAdvogado(advogadoRepository.getById(id));
		
		informacaoRepository.save(informacoes);

		Advogado advogado = advogadoRepository.findById(id).get();
		advogado.setInfoId(informacoes.getId());
		advogadoRepository.save(advogado);

		List<Especialidades> especialidades = new ArrayList<>();
		for(Especialidades esp: informacoes.getEspecialidades()){
			Especialidades espl = new Especialidades();
			espl.setAreaAtuacao(esp.getAreaAtuacao());
			espl.setInfoId(informacoes.getId());
			
			especialidades.add(espl);
			
		}
		especialidadeRepository.saveAll(especialidades);
		
		String nome = informacoes.getEspecialidades().get(informacoes.getEspecialidades().size()-1).getAreaAtuacao();
		Thread.sleep(100);
		if(nome != null) {
		Long aid = especialidadeRepository.getByName(nome);
		especialidadeRepository.deletarAid(aid);
		}
		
		List<Processos> processos = new ArrayList<>();
		for(Processos proc: informacoes.getProcessos()){
			Processos processo = proc;
			processo.setInfoId(informacoes.getId());
			processos.add(processo);
			
		}
		processoRepository.saveAll(processos);
		return ResponseEntity.ok("Salvo com sucesso!");
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Advogado advogado, @PathVariable("id") Long id){
		advogado.setId(id);
		advogadoRepository.save(advogado);
		return ResponseEntity.ok("Usuario " + id + " atualizado com sucesso.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		advogadoRepository.deleteById(id);
		return ResponseEntity.ok("Usuario " + id + " deletado com sucesso.");
	}
	

}
