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
        List<Input> inputs = inputService.listarTodosInputs();
        return new ResponseEntity<>(inputs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Input> obterInputPorId(@PathVariable Long id) {
        Optional<Input> input = inputService.obterInputPorId(id);
        if (input.isPresent()) {
            return new ResponseEntity<>(input.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Input> criarInput(@RequestBody Input input) {
        Input savedInput = inputService.criarInput(input);
        return new ResponseEntity<>(savedInput, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Input> atualizarInput(@PathVariable Long id, @RequestBody Input input) {
        Optional<Input> existingInput = inputService.obterInputPorId(id);
        if (existingInput.isPresent()) {
            input.setId(id);
            Input updatedInput = inputService.atualizarInput(input);
            return new ResponseEntity<>(updatedInput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInput(@PathVariable Long id) {
        Optional<Input> input = inputService.obterInputPorId(id);
        if (input.isPresent()) {
            inputService.deletarInput(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
