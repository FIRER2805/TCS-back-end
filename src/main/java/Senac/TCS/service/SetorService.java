package Senac.TCS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.entity.Setor;
import Senac.TCS.repository.SetorRepository;
import Senac.TCS.repository.UsuarioRepository;
import Senac.TCS.seletor.SetorSeletor;
import jakarta.transaction.Transactional;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Setor> consultarComFiltros(SetorSeletor seletor) {
		List<Setor> setores = listarTodos();

		if (seletor.temFiltro()) {
			if (seletor.getNome() != null && !seletor.getNome().trim().isBlank()) {
				setores = filtrarPorNomeSetor(setores, seletor.getNome());
			}
			if (seletor.getDescricao() != null && !seletor.getDescricao().trim().isBlank()) {
				setores = filtrarPorDescricaoSetor(setores, seletor.getDescricao());
			}

		}

		return setores;
	}

	private List<Setor> filtrarPorDescricaoSetor(List<Setor> setores, String descricao) {
		return (List<Setor>) setores.stream().filter(setor -> setor.getDescricao().equals(descricao))
				.collect(Collectors.toList());
	}

	private List<Setor> filtrarPorNomeSetor(List<Setor> setores, String nome) {
		return (List<Setor>) setores.stream().filter(setor -> setor.getNome().equals(nome))
				.collect(Collectors.toList());
	}

	public List<Setor> listarTodos() {
		return setorRepository.findAll();
	}

	@Transactional
    public Setor inserir(Setor novoSetor, Long idUsuario) throws CampoInvalidoException {
        validarCamposObrigatorios(novoSetor);
        if (novoSetor.getIdSetor() == null) {
            Setor setorSalvo = setorRepository.save(novoSetor);
            usuarioRepository.inserirUsuarioNoSetor(idUsuario, setorSalvo.getIdSetor(), true);
            return setorSalvo;
        } else {
            return atualizar(novoSetor);
        }
    }
	
	
	public Setor atualizar(Setor novoSetor) throws CampoInvalidoException {
		validarCamposObrigatorios(novoSetor);
		// fazer uma validacao para verificar se tem id antes de atualizar
		if (novoSetor.getIdSetor() != null) {
			return setorRepository.save(novoSetor);
		} else {
			throw new IllegalArgumentException("Não é possível atualizar um setor com um ID indefinido.");
		}
			
		}
	public Setor consultarPorId(long id) {
		return setorRepository.findById(id).get();
	}

	public boolean excluir(Integer id) {
		this.setorRepository.deleteById(id.longValue());
		return true;
	}

	private String validarCampoString(String valorCampo, String nomeCampo) {
		if (valorCampo == null || valorCampo.trim().isEmpty()) {
			return "Informe o " + nomeCampo + " \n";
		}
		return "";
	}

	private void validarCamposObrigatorios(Setor novoSetor) throws CampoInvalidoException {
		String mensagemValidacao = "";
		mensagemValidacao += validarCampoString(novoSetor.getNome(), "nome");
		mensagemValidacao += validarCampoString(novoSetor.getDescricao(), "descricao");
		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

}
