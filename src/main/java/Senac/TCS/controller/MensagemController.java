package Senac.TCS.controller;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.dto.MensagemDto;
import Senac.TCS.model.dto.MensagemRecebidaDTO;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemJdbcRepository;
import Senac.TCS.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:9000"}, maxAge = 3600)
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private MensagemJdbcRepository mensagemJdbcRepository;

    @GetMapping
    public ResponseEntity<List<Mensagem>> ObterTodasMensagens() {
        return new ResponseEntity<>(mensagemService.listarTodasMensagens(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> obterMensagemPorId(@PathVariable Long id) {
        return new ResponseEntity<>(mensagemService.obterMensagemPorId(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) {
    	try{
    		Mensagem mensagemSalva = mensagemService.criarMensagem(mensagem);
    		return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
    	}
    	catch(MensagemInvalidaException e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

    @GetMapping("/arvore")
    public ResponseEntity<MensagemDto> obterArvoreMensage(){
        return new ResponseEntity<>(this.mensagemJdbcRepository.obterArvoreMensagem(1l), HttpStatus.OK);
    }

    @PostMapping("/proximo")
    public ResponseEntity<Mensagem> obterProximaMensagem(@RequestBody MensagemRecebidaDTO mensagemDto){
        return new ResponseEntity<Mensagem>(mensagemService.obterProximaMensagem(mensagemDto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> atualizarMensagem(@PathVariable Long id, @RequestBody Mensagem mensagem) {
        try{
            Mensagem mensagemSalva = mensagemService.atualizarMensagem(id,mensagem);
            return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
        }
        catch(MensagemInvalidaException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensagem(@PathVariable Long id) {
        mensagemService.deletarMensagem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

