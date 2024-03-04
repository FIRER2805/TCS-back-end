package Senac.TCS.controller;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.dto.MensagemDTO;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.seletor.MensagemSeletor;
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

    @PostMapping("/proximo")
    public ResponseEntity<Mensagem> obterProximaMensagem(@RequestBody MensagemSeletor seletor){
        return new ResponseEntity<Mensagem>(mensagemService.obterProximaMensagem(seletor), HttpStatus.OK);
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

