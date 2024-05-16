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

	public Setor inserir(Setor novoFabricante) throws CampoInvalidoException {
		validarCamposObrigatorios(novoFabricante);
		return setorRepository.save(novoFabricante);
	}

	public Setor atualizar(Setor novoSetor) throws CampoInvalidoException {
		validarCamposObrigatorios(novoSetor);
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

	private void validarCamposObrigatorios(Setor setor) throws CampoInvalidoException {
		String mensagemValidacao = "";
		mensagemValidacao += validarCampoString(setor.getNome(), "nome");
		mensagemValidacao += validarCampoString(setor.getDescricao(), "descricao");
	//	mensagemValidacao += validarCampoString(setor.getUsuarios(), "usuarios");
		mensagemValidacao += validarCampoString(setor.getNome(), "nome");

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
}