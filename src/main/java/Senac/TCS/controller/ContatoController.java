package Senac.TCS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Senac.TCS.exception.ContatoException;
import Senac.TCS.model.entity.Contato;
import Senac.TCS.service.ContatoService;

@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:5500" }, maxAge = 3600)
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping
	public List<Contato> listarContatos() {
		return contatoService.listarContatos();
	}

	@PostMapping
	public Contato criarContato(@RequestBody Contato contato) {
		return contatoService.criarContato(contato);
	}

	@GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Contato>> buscarContatosPorUsuario(@PathVariable Long idUsuario) {
        List<Contato> contatos = contatoService.findContatosByUsuario(idUsuario);
        if (contatos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contatos);
    }

	@DeleteMapping("/{id}")
	public void deletarContato(@PathVariable Long id) {
		contatoService.deletarContato(id);
	}

	@PutMapping("/{id}")
	public Contato atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
		return contatoService.atualizarContato(id, contato);
	}
	@GetMapping("/existencia/{numero}")
    public ResponseEntity<String> verificarExistencia(@PathVariable String numero) {
        try {
            contatoService.existencia(numero);
            return ResponseEntity.ok("Contato com o número " + numero + " encontrado.");
        } catch (ContatoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()+ "Aleluia");
        }
    }
	
}
