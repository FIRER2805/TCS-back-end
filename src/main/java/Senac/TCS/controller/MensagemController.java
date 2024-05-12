package Senac.TCS.controller;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.dto.GrafoMensagemDto;
import Senac.TCS.model.dto.MensagemDto;
import Senac.TCS.model.dto.MensagemRecebidaDTO;
import Senac.TCS.model.dto.MensagemSalvarDto;
import Senac.TCS.model.entity.Mensagem;
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
    public ResponseEntity<Mensagem> criarMensagem(@RequestBody MensagemSalvarDto mensagem) {
    	try{
    		Mensagem mensagemSalva = mensagemService.criarMensagem(mensagem);
    		return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
    	}
    	catch(MensagemInvalidaException e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

    @GetMapping("/grafo/{idSetor}")
    public ResponseEntity<GrafoMensagemDto> obterGrafoMensagem(@PathVariable Long idSetor){
        return new ResponseEntity<>(this.mensagemService.obterGrafoMensagem(idSetor), HttpStatus.OK);
    }

    @PostMapping("/proximo")
    public ResponseEntity<MensagemDto> obterProximaMensagem(@RequestBody MensagemRecebidaDTO mensagemRecebida){
        return new ResponseEntity<MensagemDto>(mensagemService.obterProximaMensagem(mensagemRecebida), HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<Mensagem> atualizarMensagem(@RequestBody Mensagem mensagem) {
        try{
            Mensagem mensagemSalva = mensagemService.atualizarMensagem(mensagem);
            return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
        }
        catch(MensagemInvalidaException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deletarMensagem(@RequestBody Mensagem mensagem) {
        mensagemService.deletarMensagem(mensagem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

