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

import Senac.TCS.dto.SetorDTO;
import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.entity.Setor;
import Senac.TCS.seletor.SetorSeletor;
import Senac.TCS.service.SetorService;

@RestController
@ComponentScan(basePackages = "Setor.TCS.service")
@RequestMapping(path = "/setores")
@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)

public class SetorController {

	@Autowired
	private SetorService service = new SetorService();

	
	@PostMapping("/seletor")
	public List<Setor> consultarComFiltros(SetorSeletor seletor) {
		return service.consultarComFiltros(seletor);
	}

	@GetMapping("/listarTodos")
	public List<Setor> listarTodos() {
		List<Setor> setores = service.listarTodos();
		return setores;
	}

	
	@PostMapping("/inserir")
    public ResponseEntity<Setor> inserirSetor(@RequestBody SetorDTO setorDTO, @RequestParam Long idUsuario) {
        try {
            Setor setorCriado = service.inserir(new Setor(setorDTO), idUsuario);
            return ResponseEntity.ok(setorCriado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
	
	@GetMapping("/{id}")
	public Setor pesquisarPorId(@PathVariable Integer id) {
		return service.consultarPorId(id.longValue());
	}

	@PutMapping("/atualizar")
	public boolean atualizar(@RequestBody Setor setorParaAtualizar) throws CampoInvalidoException {
		return service.atualizar(setorParaAtualizar) != null;
	}
	

	@DeleteMapping("/{id}")
	public boolean excluir(@PathVariable int id) {
		return service.excluir(id);
	}

}
