package Senac.TCS.controller;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping
    public List<Mensagem> listarMensagens() {
        return mensagemService.listarTodasMensagens();
    }

    @GetMapping("/{id}")
    public Mensagem obterMensagemPorId(@PathVariable Long id) {
        return mensagemService.obterMensagemPorId(id);
    }

    @PostMapping
    public Mensagem criarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemService.criarMensagem(mensagem);
    }

    @PutMapping
    public Mensagem atualizarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemService.atualizarMensagem(mensagem);
    }

    @DeleteMapping("/{id}")
    public void deletarMensagem(@PathVariable Long id) {
        mensagemService.deletarMensagem(id);
    }
}

