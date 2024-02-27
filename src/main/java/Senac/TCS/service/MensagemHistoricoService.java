package Senac.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.entity.MensagemHistorico;
import Senac.TCS.model.repository.MensagemHistoricoRepository;

@Service
public class MensagemHistoricoService {
	
	@Autowired
	private MensagemHistoricoRepository mensagemHistoricoRepository;
	
    public List<MensagemHistorico> listarTodasMensagens() {
        return (List<MensagemHistorico>) mensagemHistoricoRepository.findAll();
    }
    
    public List<MensagemHistorico> buscarHistoricoPorIdConteudo(Long idContato){
    	return (List<MensagemHistorico>) mensagemHistoricoRepository.findByIdContato(idContato);
    }

    public MensagemHistorico obterMensagemPorId(Long id) {
    	return mensagemHistoricoRepository.findById(id).orElse(null);
    }

    public MensagemHistorico criarMensagem(MensagemHistorico mensagemHistorico) throws MensagemInvalidaException {
    	String erro = this.validarMensagem(mensagemHistorico);
    	if(!erro.isEmpty()) {
    		throw new MensagemInvalidaException(erro);
    	}
        return mensagemHistoricoRepository.save(mensagemHistorico);
    }

    public MensagemHistorico atualizarMensagem(Long id, MensagemHistorico mensagemHistorico) throws MensagemInvalidaException {
		String erro = this.validarMensagem(id,mensagemHistorico);
		if(!erro.isEmpty()){
			throw new MensagemInvalidaException(erro);
		}
		mensagemHistorico.setId(id);
		return mensagemHistoricoRepository.save(mensagemHistorico);
    }

    public void deletarMensagem(Long id) {
        mensagemHistoricoRepository.deleteById(id);
    }
    
    private String validarMensagem(MensagemHistorico mensagemHistorico) {
    	String erro = "";
    	if(mensagemHistorico.getConteudo().isBlank()) {
    		erro += "Conteudo é obrigatório";
    	}
    	return erro;
    }

	private String validarMensagem(Long id, MensagemHistorico mensagemHistorico){
		String erro = "";
		if(id == null){
			erro += "É necessario colocar id para atualizar a mensagem \n";
		}
		erro += this.validarMensagem(mensagemHistorico);
		return erro;
	}
}
