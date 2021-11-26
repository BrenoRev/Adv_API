package com.dev.adv.controllers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
		
		// Busca uma lista de usuarios e seta os processos, informações e especialidades
		
		List<Advogado> advogados = advogadoRepository.findAll();
		
		for(Advogado adv: advogados) {
	
			List<Especialidades> esp = especialidadeRepository.getEspecialidades(adv.getId());
			
			List<Processos> proc = processoRepository.getProcessos(adv.getId());
			adv.getInformacoes().setEspecialidades(esp);
			adv.getInformacoes().setProcessos(proc);
			adv.getInformacoes().setCausasGanhas(processoRepository.win(adv.getId()));
			adv.getInformacoes().setCausasPerdidas(processoRepository.lose(adv.getId()));
			LocalDateTime now = LocalDateTime.now();
			long meses = adv.getInformacoes().getStartAdvogacia().until(now, ChronoUnit.MONTHS);
			adv.getInformacoes().setTempoAdvogacia(meses);
		}
		
		return ResponseEntity.ok(advogados);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Advogado> findById(@PathVariable("id") Long id){
		
		// Busca um usuario e seta os processos, informações e especialidades
		Advogado advogado = advogadoRepository.findById(id).get();
		List<Especialidades> esp = especialidadeRepository.getEspecialidades(id);
		List<Processos> proc = processoRepository.getProcessos(id);
		advogado.getInformacoes().setEspecialidades(esp);
		advogado.getInformacoes().setProcessos(proc);
		advogado.getInformacoes().setCausasGanhas(processoRepository.win(id));
		advogado.getInformacoes().setCausasPerdidas(processoRepository.lose(id));
		advogado.getInformacoes().getStartAdvogacia();
		
		// Calcula o tempo de advocacia do advogado com base no ano que ele começou e retorna os meses
		LocalDateTime now = LocalDateTime.now();
		long meses = advogado.getInformacoes().getStartAdvogacia().until(now, ChronoUnit.MONTHS);
		advogado.getInformacoes().setTempoAdvogacia(meses);

		return ResponseEntity.ok(advogado);
	}
	 
	@PostMapping("/")
	public ResponseEntity<String> register(@Valid @RequestBody Advogado advogado){
		advogadoRepository.save(advogado);		 
		return ResponseEntity.ok("Usuario " + advogado.getId() + " Salvo com sucesso!");
	}
	
	@PostMapping("/informacoes/{id}")
	public ResponseEntity<String> registerInfo(@PathVariable("id") Long id, @RequestBody Informacoes informacoes) throws InterruptedException{
		
		if(informacaoRepository.exist(id) != 0) {
			return ResponseEntity.ok("Já existe um documento vinculado a esse advogado");
		}else {
		
		// Salva a informação junto com o id do advogado
		informacoes.setAdvogado(advogadoRepository.getById(id));
		informacaoRepository.save(informacoes);
		
		// Busca o advogado e coloca o id da informação nele
		Advogado advogado = advogadoRepository.findById(id).get();
		advogado.setInfoId(informacoes.getId());
		advogadoRepository.save(advogado);

		//Adiciona as especialidades ao advogado
		
		List<Especialidades> especialidades = new ArrayList<>();
		for(Especialidades esp: informacoes.getEspecialidades()){
			Especialidades espl = new Especialidades();
			espl.setAreaAtuacao(esp.getAreaAtuacao());
			espl.setInfoId(informacoes.getId());
			especialidades.add(espl);
		}
		especialidadeRepository.saveAll(especialidades);
		
		// Para remover o bug da duplicação da tabela um por um sendo salvo em 1 json
		String nome = informacoes.getEspecialidades().get(informacoes.getEspecialidades().size()-1).getAreaAtuacao();
		Thread.sleep(100);
		if(nome != null) {
		Long aid = especialidadeRepository.getByName(nome);
		especialidadeRepository.deletarAid(aid);
		}
		
		//Adiciona a lista de processos ao advogado
		List<Processos> processos = new ArrayList<>();
		for(Processos proc: informacoes.getProcessos()){
			Processos processo = proc;
			processo.setInfoId(informacoes.getId());
			processos.add(processo);
		}
		processoRepository.saveAll(processos);
		
		// Seta a quantidade de processos ganhos e perdidos com base nos processos enviados
		Informacoes inf = informacaoRepository.findById(informacoes.getId()).get();
		inf.setCausasGanhas(processoRepository.win(id));
		inf.setCausasPerdidas(processoRepository.lose(id));
		informacaoRepository.save(inf); 
		
		return ResponseEntity.ok("Salvo com sucesso!");
	} 
	}
	
	// Atualizar um advogado
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Advogado advogado, @PathVariable("id") Long id){
		advogado.setId(id);
		advogadoRepository.save(advogado);
		return ResponseEntity.ok("Usuario " + id + " atualizado com sucesso.");
	}
	
	// Atualizar uma informação do advogado
	
	@PutMapping("/informacoes/{id}")
	public ResponseEntity<String> updateInformacoes(@Valid @RequestBody Informacoes informacoes, @PathVariable("id") Long id){
		informacoes.setId(id);
		informacaoRepository.save(informacoes);
		return ResponseEntity.ok("Informacao com o id " + id + " atualizado com sucesso.");
	}
	
	// Deletar um advogado e suas informações
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		advogadoRepository.deleteById(id);
		return ResponseEntity.ok("Usuario " + id + " deletado com sucesso.");
	}
	

	
	

}
