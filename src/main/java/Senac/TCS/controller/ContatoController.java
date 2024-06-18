package Senac.TCS.controller;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Contato adicionarAoCarrinho(@RequestBody Contato contato) {
        return contatoService.criarContato(contato);
    }
    @GetMapping("/usuario/{id}")
    public Optional<Contato> getCarrinhoByUserId(@PathVariable Long id)  {
        return contatoService.buscarContatosPorUsuario(id);
    }

    @GetMapping("/{id}")
    public Optional<Contato> buscarContatosPorUsuario (@PathVariable Long id) {
        return contatoService.buscarContatosPorUsuario(id);
    }

    @PostMapping("/automatizado")
    public ResponseEntity estaAutomatizado(@RequestBody Contato contato){
        Contato contatoRetornado = this.contatoService.estaAutomatizado(contato);
        return ResponseEntity.ok().body(contatoRetornado);
    }

    @PostMapping("atendimentoManual")
    public ResponseEntity atendimentoManual(@RequestBody Contato contato){
        Contato contatoRetornado = this.contatoService.atendimentoManual(contato);
        return ResponseEntity.ok().body(contatoRetornado);
    }
    
    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Long id)  {
        contatoService.deletarContato(id);
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
        return contatoService.atualizarContato(id, contato);
    }
}

