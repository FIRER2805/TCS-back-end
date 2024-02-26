package Senac.TCS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
