package Senac.TCS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.exception.UsuarioInvalidoException;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.service.UsuarioService;

@RestController
@ComponentScan(basePackages = "Usuario.TCS.service")
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
public class UsuarioController {

	@Autowired
	UsuarioService service = new UsuarioService();
	@Autowired
    private UsuarioService usuarioService;

	@PostMapping
	public Usuario criar(@RequestBody Usuario usuario) {

		try {
			usuario = service.criar(usuario);
		} catch (UsuarioInvalidoException | CampoInvalidoException erro) {
			erro.getMessage();
		}
		return usuario;
	}

	@GetMapping("/listarTodos")
	public List<Usuario> buscarTodos() {
		return service.buscarTodos();
	}
	
	@PostMapping("/setorInserirUsuario")
    public ResponseEntity<Void> adicionarUsuarioAoSetor(@RequestParam Long idUsuario, @RequestParam Long idSetor, @RequestParam boolean administrador) {
        usuarioService.inserirUsuarioNoSetor(idUsuario, idSetor, administrador);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/setor/{idSetor}")
    public List<Usuario> listarUsuariosPorSetor(@PathVariable Long idSetor) {
        return usuarioService.listarUsuariosPorSetor(idSetor);
    }

	@GetMapping("/{id}")
	public Usuario buscarPorID(@PathVariable Long id) {
		return service.buscarPorID(id);
	}

	@PutMapping("/{id}")
	public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
		try {
			usuario = service.atualizarUsuario(usuario);
		} catch (CampoInvalidoException erro) {
			erro.getMessage();
		}

		return usuario;
	}

	@DeleteMapping("/{id}")
	public void excluirPorId(@PathVariable Long id) {
		service.excluirPorId(id);
	}

	public Usuario efetuarLogin(Usuario usuario) {

		try {
			 usuario = service.efetuarLogin(usuario);
		} catch (CampoInvalidoException erro) {
			erro.getMessage();
		}
		return usuario;
	}

	public void recuperarSenha(String email) {

	}

}