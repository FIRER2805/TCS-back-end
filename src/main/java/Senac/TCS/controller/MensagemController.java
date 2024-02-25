package Senac.TCS.controller;

import Senac.TCS.model.dto.mensagemDTO;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
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
/*
    @GetMapping("/root/{idSetor}")
    public ResponseEntity<Mensagem> obterMensagemRoot(@PathVariable Long idSetor) {
        return new ResponseEntity<>(mensagemService.obterMensagemRoot(idSetor), HttpStatus.FOUND);
    }*/

    @PostMapping
    public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) {
        return new ResponseEntity<>(mensagemService.criarMensagem(mensagem), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mensagem> atualizarMensagem(@RequestBody Mensagem mensagem) {
        return new ResponseEntity<>(mensagemService.atualizarMensagem(mensagem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensagem(@PathVariable Long id) {
        mensagemService.deletarMensagem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

