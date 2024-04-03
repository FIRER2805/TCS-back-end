package Senac.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.model.entity.Telefone;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.model.repository.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public Telefone criar(Telefone tel) {
		return telefoneRepository.save(tel);
	}
	
	public Telefone buscarPorId(Long id) {
		
		return telefoneRepository.findById(id).orElse(null);
		
	}
	
	public List<Telefone> buscarTodos() {
		return telefoneRepository.findAll();
	}
	
	public Telefone atualizar(Telefone tel) {
		return telefoneRepository.save(tel);
	}
	
	public void excluirPorId(Long id) {
		telefoneRepository.deleteById(id);
	}
	
}
