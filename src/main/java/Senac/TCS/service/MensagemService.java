package Senac.TCS.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Senac.TCS.model.dto.*;
import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Setor;
import Senac.TCS.model.repository.InputRepository;
import Senac.TCS.model.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	@Autowired
	private InputService inputService;
	@Autowired
	private SetorRepository setorRepository;

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
		System.out.println(mensagemRecebida.getIdMensagemPai());
		long idSetor = mensagemRecebida.getIdSetor();
		Mensagem mensagemEnviar;
		Long minutosUltimaInteracao = this.tempoUltimaMensagemEmMinutos(mensagemRecebida);
		// mostra mensagem raiz se tiver passado 5 minutos ou
		// se for a primeira mensagem daquele contato para aquele ususario mostra mensagem raiz
		if(minutosUltimaInteracao == null
				|| minutosUltimaInteracao > TEMPO_RESET_CONVERSA
				|| mensagemRecebida.getIdMensagemPai() == null) {
			mensagemEnviar = this.mensagemRepository.obterMensagemRoot(idSetor);
		}
		else {
			mensagemEnviar = this.mensagemRepository.obterMensagemFilha(mensagemRecebida.getInputPai(),
					mensagemRecebida.getIdMensagemPai(), idSetor);
		}

		MensagemDto retorno;

		// buscando os inputs aceitos
		//o usuário enviou tudo certo
		if(mensagemEnviar != null ){
			List<Input> inputsValidos = this.inputRepository.obterInputsValidosDeMensagem(mensagemEnviar.getId(), idSetor);
			retorno = new MensagemDto(mensagemEnviar, inputsValidos);
		}
		// o usuário enviou errado
		else{
			List<Input> inputsValidos = this.inputRepository.obterInputsValidosDeMensagem(mensagemRecebida.getIdMensagemPai(), idSetor);
			retorno = MensagemDto.builder()
					.id(mensagemRecebida.getIdMensagemPai())
					.idSetor(mensagemRecebida.getIdSetor())
					.conteudo("input incorreto")
					.inputsFilhos(inputsValidos)
					.build();
		}

		return retorno;
    }

    public Mensagem criarMensagem(MensagemSalvarDto mensagem) throws MensagemInvalidaException {
    	String erro = this.validarMensagem(mensagem);
    	if(!erro.isEmpty()) {
    		throw new MensagemInvalidaException(erro);
    	}
		Mensagem novaMensagem = Mensagem.builder()
				.idSetor(mensagem.getIdSetor())
				.conteudo(mensagem.getConteudo())
				.build();
        Mensagem mensagemSalva = mensagemRepository.save(novaMensagem);

		if(mensagem.getInputPai() != null){
			Input inputSalvo = inputService.salvarInput(mensagemSalva, mensagem.getInputPai());
		}
		return mensagemSalva;
    }

    public Mensagem atualizarMensagem(Mensagem mensagem) throws MensagemInvalidaException {
//		String erro = this.validarMensagem(id,mensagem);
//		if(!erro.isEmpty()){
//			throw new MensagemInvalidaException(erro);
//		}
		return mensagemRepository.save(mensagem);
    }

    public void deletarMensagem(Mensagem m) {
		deletarMensagemCascata(m);
		inputRepository.deleteByIdMensagemFilha(null);
    }

	public void deletarMensagemCascata(Mensagem m){
		List<Input> inputsFilhas = this.inputRepository.obterInputsValidosDeMensagem(m.getId(), m.getIdSetor());
		for(Input i: inputsFilhas){
			Mensagem mensagemFilha = this.mensagemRepository.findById(i.getIdMensagemFilha()).get();
			deletarMensagemCascata(mensagemFilha);
			this.inputRepository.delete(i);
		}
		mensagemRepository.deleteById(m.getId());
	}
    
    private String validarMensagem(MensagemSalvarDto mensagem) {
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

//	private String validarMensagem(Long id, Mensagem mensagem){
//		String erro = "";
//		if(id == null){
//			erro += "É necessario colocar id para atualizar a mensagem \n";
//		}
//		erro += this.validarMensagem(mensagem);
//		return erro;
//	}

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

	public List<Setor> selecionarSetor(MensagemRecebidaDTO mensagemRecebidaDTO) {
		return this.setorRepository.obterSetoresDeUsuario(mensagemRecebidaDTO.getIdUsuario());
	}
}
