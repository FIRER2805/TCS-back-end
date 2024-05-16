package Senac.TCS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Senac.TCS.entities.Setor;
import Senac.TCS.exception.CampoInvalidoException;
import Setor.TCS.service.SetorService;

@RestController
@RequestMapping(path = "/api/setores")
@CrossOrigin(origins = { "http://localhost:4200"}, maxAge = 3600)

public class SetorController {
	
	@Autowired
	private SetorService service;
	
 /// ola
	
	@GetMapping
	public List<Setor> listarTodos() {
		List<Setor> setores = service.listarTodos();
		return setores;
	}
	
	@PostMapping
	public Setor salvar(@RequestBody Setor novo) 
			throws CampoInvalidoException {
		
		return service.inserir(novo);
	}
	
	@GetMapping("/{id}")
	public Setor pesquisarPorId(@PathVariable Integer id){
		return service.consultarPorId(id.longValue());
	}
	
	@PutMapping
	public boolean atualizar(@RequestBody Setor setorParaAtualizar) 
			throws CampoInvalidoException {
		return service.atualizar(setorParaAtualizar) != null;
	}
	
	@DeleteMapping("/{id}")
	public boolean excluir(@PathVariable Integer id) {
		return service.excluir(id);
	}
	
	

}
