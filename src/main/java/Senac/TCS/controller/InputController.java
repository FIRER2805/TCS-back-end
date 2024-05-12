package Senac.TCS.controller;

import Senac.TCS.model.entity.Input;
import Senac.TCS.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:9000"}, maxAge = 3600)
public class InputController {
    @Autowired
    private InputService inputService;

    @PutMapping
    public ResponseEntity<Input> atualizar(@RequestBody Input input){
        return new ResponseEntity<>(this.inputService.atualizarInput(input), HttpStatus.OK);
    }
}
