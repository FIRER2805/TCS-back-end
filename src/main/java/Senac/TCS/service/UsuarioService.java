package Senac.TCS.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.exception.UsuarioInvalidoException;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario criar(Usuario usuario) throws UsuarioInvalidoException, CampoInvalidoException {

		validarAtributosDeUsuario(usuario);

		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new UsuarioInvalidoException("Email ja Cadastrado");
		}

		return usuarioRepository.save(usuario);
	}

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
	
	 public List<Usuario> listarUsuariosPorSetor(Long idSetor) {
	        return usuarioRepository.findUsuariosByIdSetor(idSetor);
	    }

	public Usuario buscarPorID(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario atualizarUsuario(Usuario usuario) throws CampoInvalidoException {

		validarAtributosDeUsuario(usuario);

		return usuarioRepository.save(usuario);
	}

	public void excluirPorId(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario efetuarLogin(Usuario usuario) throws CampoInvalidoException {

		String mensagemErro = "";

		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			mensagemErro += "";
		}

		if (usuarioRepository.existsBySenha(usuario.getEmail())) {
			mensagemErro += "";
		}

		if (mensagemErro.isBlank()) {
			throw new CampoInvalidoException(mensagemErro);
		} else {
			usuario = usuarioRepository.findByEmail(usuario.getEmail());
		}

		return usuario;
	}

	public void validarAtributosDeUsuario(Usuario usuario) throws CampoInvalidoException {

		String mensagem = "";
		if (validarNome(usuario.getNome())) {
			mensagem += "\nNome invalido";
		}
		if (validarEmail(usuario.getEmail())) {
			mensagem += "\nEmail invalido";
		}
		if (validarSenha(usuario.getSenha())) {
			mensagem += "\nSenha invalido";
		}

		if (mensagem.isBlank()) {
			throw new CampoInvalidoException(mensagem);
		}

	}

	private boolean validarNome(String nome) {
		return nome.matches("X{3,}");
	}

	private boolean validarEmail(String email) {
		return email.matches("[\\w.-]+@gmail.com$");
	}

	private boolean validarSenha(String senha) {
		return senha.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$");
	}

	public String recuperarSenha(String email) {
		SecureRandom gerador = new SecureRandom();
		StringBuilder senha = new StringBuilder();

		String Letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		for (int i = 0; i < 8; i++) {
			int indiceAleatorio = gerador.nextInt(Letras.length());
			senha.append(Letras.charAt(indiceAleatorio));
		}

		return senha.toString();
	}

}