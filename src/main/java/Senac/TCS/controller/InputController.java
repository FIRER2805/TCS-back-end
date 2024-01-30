package Senac.TCS.controller;

import Senac.TCS.model.entity.Input;
import Senac.TCS.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inputs")
public class InputController {

    @Autowired
    private InputService inputService;

    @GetMapping
    public ResponseEntity<List<Input>> obterTodosInputs() {
        return new ResponseEntity<>(inputService.listarTodosInputs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Input> obterInputPorId(@PathVariable Long id) {
        return new ResponseEntity<>( inputService.obterInputPorId(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Input> criarInput(@RequestBody Input input) {
        return new ResponseEntity<>(inputService.criarInput(input), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Input> atualizarInput(@RequestBody Input input) {
        return new ResponseEntity<>(inputService.atualizarInput(input), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInput(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
