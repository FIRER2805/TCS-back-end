package Senac.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;
import Senac.TCS.model.seletor.MensagemSeletor;
import Senac.TCS.model.specification.MensagemSpecification;

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
    
    public Mensagem obterProximaMensagem(MensagemSeletor seletor) {
    	Specification<Mensagem> query = MensagemSpecification.proximaMensagem(seletor);
    	return mensagemRepository.findOne(query).get();
    }

    public Mensagem criarMensagem(Mensagem mensagem) throws MensagemInvalidaException {
    	String erro = this.validarMensagem(mensagem);
    	if(!erro.isEmpty()) {
    		throw new MensagemInvalidaException(erro);
    	}
        return mensagemRepository.save(mensagem);
    }

    public Mensagem atualizarMensagem(Long id, Mensagem mensagem) throws MensagemInvalidaException {
		String erro = this.validarMensagem(id,mensagem);
		if(!erro.isEmpty()){
			throw new MensagemInvalidaException(erro);
		}
		mensagem.setId(id);
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
    				!(mensagem.getInputPai() == null || mensagem.getInputPai().isBlank())) {
    			erro += "Mensagem root não pode ter inputPai \n";
    		}
    		// verifica se for mensagem filha, ela precisa ter inputPai
    		if(!(mensagem.getMensagemPai() == null || mensagem.getMensagemPai().getId() == null) &&
    				(mensagem.getInputPai() == null || mensagem.getInputPai().isBlank())) {
    			erro += "Mensagem filha precisa ter inputPai \n";
    		}
    	}
    	return erro;
    }

	private String validarMensagem(Long id, Mensagem mensagem){
		String erro = "";
		if(id == null){
			erro += "É necessario colocar id para atualizar a mensagem \n";
		}
		erro += this.validarMensagem(mensagem);
		return erro;
	}
}
