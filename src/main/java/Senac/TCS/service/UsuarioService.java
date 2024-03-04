package Senac.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	Usuario ur = new Usuario();

	public Usuario criar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorID(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario atualizarPorId(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void excluirPorId(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public void validarAtributosDeUsuario(Usuario usuario) throws CampoInvalidoException {
		
		String mensagem ="";
		if(validarNome(usuario.getNome())) {
			mensagem += "\nNome invalido";
		}
		if(validarEmail(usuario.getEmail())) {
			mensagem += "\nEmail invalido";
		}
		if(validarSenha(usuario.getSenha())) {
			mensagem += "\nSenha invalido";
		}
		
		if(mensagem.isBlank()) {
			throw new CampoInvalidoException(mensagem);
		}
		
	}
	
	public boolean validarNome(String nome) {
		return nome.matches("X{n,}");
	}
	
	public boolean validarEmail(String email) {
		return email.matches("[\\w.-]+@gmail.com$");
	}
	
	public boolean validarSenha(String senha) {
		return senha.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$");
	}
	
}
