package Senac.TCS.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.dto.MensagemDTO;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;
import Senac.TCS.model.specification.MensagemSpecification;

@Service
public class MensagemService {

	final private int TEMPO_RESET_CONVERSA = 5;

    @Autowired
    private MensagemRepository mensagemRepository;
    
    @Autowired
    private MensagemHistoricoService mensagemHistoricoService;

	@Autowired
	private ContatoService contatoService;

    public List<Mensagem> listarTodasMensagens() {
        return (List<Mensagem>) mensagemRepository.findAll();
    }

    public Mensagem obterMensagemPorId(Long id) {
    	return mensagemRepository.findById(id).orElse(null);
    }
    
    public Mensagem obterProximaMensagem(MensagemDTO mensagemDTO) {
		Specification<Mensagem> query;
		long diferencaMinutos = this.tempoDesdeUltimaMensagemEmMinutos(mensagemDTO);
		if(diferencaMinutos > TEMPO_RESET_CONVERSA || mensagemDTO.getIdMensagemPai() == null) {
			query = MensagemSpecification.mensagemRoot(mensagemDTO);
		}
		else {
			query = MensagemSpecification.proximaMensagem(mensagemDTO);
		}

    	Optional<Mensagem> mensagemRetorno = mensagemRepository.findOne(query);

		Mensagem retorno;
		List<String> inputsValidos;
		if(mensagemRetorno.isPresent()){
			retorno = mensagemRetorno.get();
			inputsValidos = this.obterInputsValidos(mensagemDTO.getIdSetor(), retorno.getId());
		}
		else{
			retorno = this.mensagemErro(mensagemDTO);
			inputsValidos = this.obterInputsValidos(mensagemDTO.getIdSetor(), retorno.getIdMensagemPai());
		}

		for(String input : inputsValidos){
			retorno.setConteudo(retorno.getConteudo() + " \n " + input);
		}

		return retorno;
    }

	private boolean verificarExistenciaMensagemPorInput(String input){
		return mensagemRepository.existsByInputPai(input);
	}

	private List<String> obterInputsValidos(Long idSetor, Long idMensagemPai){
		return mensagemRepository.obterInputsValidos(idSetor, idMensagemPai);
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
    		if((mensagem.getIdMensagemPai() == null) &&
    				!(mensagem.getInputPai() == null || mensagem.getInputPai().isBlank())) {
    			erro += "Mensagem root não pode ter inputPai \n";
    		}
    		// verifica se for mensagem filha, ela precisa ter inputPai
    		if((mensagem.getIdMensagemPai() != null) &&
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

	private Long tempoDesdeUltimaMensagemEmMinutos(MensagemDTO mensagemDTO){
		Long retorno = null;
		if(contatoService.obterContatoPorNumero(mensagemDTO.getNumeroContato()) != null){
			LocalDateTime tempoUltimaMensagem = mensagemHistoricoService.obterTempoUltimaMensagemRecebidaPorContato(mensagemDTO);
			retorno = ChronoUnit.MINUTES.between(tempoUltimaMensagem, LocalDateTime.now());
		}
		return retorno;
	}

	private Mensagem mensagemErro(MensagemDTO mensagemDTO){
		Mensagem mensagemErro = new Mensagem();
		mensagemErro.setIdMensagemPai(mensagemDTO.getIdMensagemPai());
		mensagemErro.setIdSetor(mensagemDTO.getIdSetor());
		mensagemErro.setConteudo("Input Invalido");
		return mensagemErro;
	}
}
