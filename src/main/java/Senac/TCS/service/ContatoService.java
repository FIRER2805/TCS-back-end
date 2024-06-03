package Senac.TCS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.ContatoException;
import Senac.TCS.model.entity.Contato;
import Senac.TCS.model.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public Contato criarContato(Contato contato) {
		
	    String numero = contato.getNumero();
	    String numeroCorrigido = corrigirNumero(numero);
	    contato.setNumero(numeroCorrigido);
	    if(!contatoRepository.existsByNumero(numeroCorrigido)) {
	    contato = contatoRepository.save(contato);	
	    }
	    return contato;
	}
	private String corrigirNumero(String numero) {
	    String target = "@c.us";
	    String replacement = "";
	    return numero.replace(target, replacement);
	}

	public Optional<Contato> buscarContatoPorId(Long id) {
		return contatoRepository.findById(id);
	}

	public Contato atualizarContato(Long id, Contato novoContato) {
		Contato cont = new Contato(); 
		if (contatoRepository.existsById(id)) {
			novoContato.setId(id);
			cont = contatoRepository.save(novoContato);
		}
		return cont;
	}

	public void deletarContato(Long id) throws ContatoException {
		if(contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
		}else{
			 throw new ContatoException("Contato com o id '" + id + "' não encontrado.");
		}
		
	}
	
	public List<Contato> contatoMensagemRec() {
		return contatoRepository.findContatoByMostRecentMessage();
	}
	
	public List<Contato> listarContatos() {
		return (List) contatoRepository.findAll();
	}
	public void existencia(String numero) throws ContatoException {
	    if (!contatoRepository.existsByNumero(numero)) {
	        throw new ContatoException("Contato com o número " + numero + " não encontrado.");
	    }
	}
	public List<Contato> findContatosByUsuario(Long idUsuario) {
		return contatoRepository.findContatosByUsuario(idUsuario);
       
    }

}
