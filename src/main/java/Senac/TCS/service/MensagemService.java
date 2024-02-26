package Senac.TCS.service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> listarTodasMensagens() {
        return (List<Mensagem>) mensagemRepository.findAll();
    }

    public Mensagem obterMensagemPorId(Long id) {
    	return mensagemRepository.findById(id).orElse(null);
    }

    public Mensagem criarMensagem(Mensagem mensagem) throws MensagemInvalidaException {
    	String erro = this.validarMensagem(mensagem);
    	if(!erro.isEmpty()) {
    		throw new MensagemInvalidaException(erro);
    	}
        return mensagemRepository.save(mensagem);
    }

    public Mensagem atualizarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public void deletarMensagem(Long id) {
        mensagemRepository.deleteById(id);
    }
    
    private String validarMensagem(Mensagem mensagem) {
    	String erro = "";
    	if(mensagem == null) {
    		erro = "Não há nenhuma mensagem";
    	}
    	else {
    		if(mensagem.getConteudo().isBlank()) {
    			erro += "Não há conteudo na mensagem \n";
    		}
    		// verifica caso for mensagem root, não pode ter um input pai
    		if((mensagem.getMensagemPai() == null || mensagem.getMensagemPai().getId() == null) &&
    				(mensagem.getInputPai() == null || mensagem.getInputPai().isBlank())) {
    			erro += "Mensagem root não pode ter inputPai";
    		}
    	}
    	return erro;
    }
}
