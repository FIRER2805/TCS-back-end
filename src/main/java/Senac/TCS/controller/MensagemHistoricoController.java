package Senac.TCS.controller;

import java.util.List;

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

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.entity.MensagemHistorico;
import Senac.TCS.service.MensagemHistoricoService;
import Senac.TCS.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:9000"}, maxAge = 3600)
public class MensagemHistoricoController {
	
	@Autowired
	MensagemHistoricoService mensagemHistoricoService;

    @GetMapping("/{idContato}")
    public ResponseEntity<List<MensagemHistorico>> historicoMensagemPorContato(@PathVariable Long idContato) {
        return new ResponseEntity<>(mensagemHistoricoService.buscarHistoricoPorIdConteudo(idContato), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemHistorico> obterMensagemPorId(@PathVariable Long id) {
        return new ResponseEntity<>(mensagemHistoricoService.obterMensagemPorId(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<MensagemHistorico> inserirMensagem(@RequestBody MensagemHistorico mensagemHistorico) {
    	try{
    		MensagemHistorico mensagemSalva = mensagemHistoricoService.criarMensagem(mensagemHistorico);
    		return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
    	}
    	catch(MensagemInvalidaException e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemHistorico> atualizarMensagem(@PathVariable Long id, @RequestBody MensagemHistorico mensagemHistorico) {
        try{
            MensagemHistorico mensagemSalva = mensagemHistoricoService.atualizarMensagem(id,mensagemHistorico);
            return new ResponseEntity<>(mensagemSalva, HttpStatus.OK);
        }
        catch(MensagemInvalidaException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensagem(@PathVariable Long id) {
        mensagemHistoricoService.deletarMensagem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
