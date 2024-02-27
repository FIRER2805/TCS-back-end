package Senac.TCS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.model.entity.Contato;
import Senac.TCS.model.repository.ContatoRepository;

	@Service
	public class ContatoService {

	    @Autowired
	    private ContatoRepository contatoRepository;

	    public Contato criarContato(Contato contato) {
	        return contatoRepository.save(contato);
	    }

	    public Optional<Contato> buscarContatosPorUsuario(Long idUsuario) {
	        return contatoRepository.findById(idUsuario);
	    }
	    
	    public Optional<Contato> buscarContatoPorId(Long id) {
	        return contatoRepository.findById(id);
	    }

	    public Contato atualizarContato(Long id, Contato novoContato) {
	        if (contatoRepository.existsById(id)) {
	            novoContato.setId(id);
	            return contatoRepository.save(novoContato);
	        }
	        return null; 	    }

	    public void deletarContato(Long id) {
	        contatoRepository.deleteById(id);
	 
	    }
	    public List <Contato> listarContatos() {
			return contatoRepository.findAll();
		}
	    
	}


