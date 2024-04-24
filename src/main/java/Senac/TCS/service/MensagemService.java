package Senac.TCS.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Senac.TCS.model.dto.*;
import Senac.TCS.model.entity.Input;
import Senac.TCS.model.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;

@Service
public class MensagemService {

	final private int TEMPO_RESET_CONVERSA = 5;
    @Autowired
    private MensagemRepository mensagemRepository;
	@Autowired
	private InputRepository inputRepository;
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

	public GrafoMensagemDto obterGrafoMensagem(Long idSetor){
		List<Mensagem> mensagens = this.mensagemRepository.findByIdSetor(idSetor);
		List<Input> inputs = this.inputRepository.obterInputsPorIdSetor(idSetor);
		List<NodeGrafoDto> nodes = this.mensagensParaNodeGrafoDto(mensagens);
		List<EdgeGrafoDto> edges = this.inputsParaEdgeGrafoDto(inputs);
		GrafoMensagemDto grafoMensagemDto = GrafoMensagemDto.builder()
				.nodes(nodes)
				.edges(edges)
				.build();
		return grafoMensagemDto;
	}
    
    public MensagemDto obterProximaMensagem(MensagemRecebidaDTO mensagemRecebida) {
		long idSetor = mensagemRecebida.getIdSetor();
		Mensagem mensagemEnviar;
		long minutosUltimaInteracao = this.tempoUltimaMensagemEmMinutos(mensagemRecebida);
		if(minutosUltimaInteracao > TEMPO_RESET_CONVERSA || mensagemRecebida.getIdMensagemPai() == null) {
			mensagemEnviar = this.mensagemRepository.obterMensagemRoot(idSetor);
		}
		else {
			mensagemEnviar = this.mensagemRepository.obterMensagemFilha(mensagemRecebida.getConteudo(),
					mensagemRecebida.getIdMensagemPai(), idSetor);
		}

		MensagemDto retorno;

		if(mensagemEnviar != null){
			List<Input> inputsValidos = this.inputRepository.obterInputsValidosDeMensagem(mensagemEnviar.getId(), idSetor);
			retorno = new MensagemDto(mensagemEnviar, inputsValidos);
		}
		else{
			List<Input> inputsValidos = this.inputRepository.obterInputsValidosDeMensagem(mensagemRecebida.getIdMensagemPai(), idSetor);
			retorno = MensagemDto.builder()
					.id(mensagemRecebida.getId())
					.conteudo("inputs inválidos")
					.idSetor(idSetor)
					.inputsFilhos(inputsValidos)
					.build();
		}

		return retorno;
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

	private Long tempoUltimaMensagemEmMinutos(MensagemRecebidaDTO mensagemRecebida){
		Long retorno = null;
		if(contatoService.obterContatoPorNumero(mensagemRecebida.getNumeroContato()) != null){
			LocalDateTime tempoUltimaMensagem = mensagemHistoricoService.obterTempoUltimaMensagemRecebidaPorContato(mensagemRecebida);
			retorno = ChronoUnit.MINUTES.between(tempoUltimaMensagem, LocalDateTime.now());
		}
		return retorno;
	}

	private List<NodeGrafoDto> mensagensParaNodeGrafoDto(List<Mensagem> mensagens){
		List<NodeGrafoDto> nodes = new ArrayList<>();
		for(Mensagem m: mensagens){
			NodeGrafoDto node = new NodeGrafoDto(m);
			nodes.add(node);
		}
		return nodes;
	}

	private List<EdgeGrafoDto> inputsParaEdgeGrafoDto(List<Input> inputs){
		List<EdgeGrafoDto> edges = new ArrayList<>();
		for(Input i: inputs){
			EdgeGrafoDto edge = new EdgeGrafoDto(i);
			edges.add(edge);
		}
		return edges;
	}
}
