package Setor.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.entities.Setor;
import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.repository.SetorRepository;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

	public List<Setor> listarTodos() {
		return setorRepository.findAll();
	}

	public Setor inserir(Setor novoSetor) throws CampoInvalidoException {
		validarCamposObrigatorios(novoSetor); 
		return setorRepository.save(novoSetor);
	}

	public Setor atualizar(Setor novoSetor) throws CampoInvalidoException {
		validarCamposObrigatorios(novoSetor);
		//fazer uma validacao para verificar se tem id antes de atualizar
		return setorRepository.save(novoSetor);
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
