package Senac.TCS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Senac.TCS.model.entity.Usuario;
import Senac.TCS.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service = new UsuarioService();

	@PostMapping
	public Usuario criar(@RequestBody Usuario usuario) {
		return service.criar(usuario);
	}

	@GetMapping
	public List<Usuario> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping("/{id}")
	public Usuario buscarPorID(@PathVariable Long id) {
		return service.buscarPorID(id);
	}

	@PutMapping("/{id}")
	public Usuario atualizarPorId(@RequestBody Usuario usuario) {
		return service.atualizarPorId(usuario);
	}
	
	@DeleteMapping("/{id}")
	public void excluirProduto(@PathVariable Long id) {
		service.excluirPorId(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
