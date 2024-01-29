package Senac.TCS.service;

import Senac.TCS.model.entity.Input;
import Senac.TCS.model.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    private InputRepository inputRepository;

    public List<Input> listarTodosInputs() {
        return (List) inputRepository.findAll();
    }

    public Optional<Input> obterInputPorId(Long id) {
        return inputRepository.findById(id);
    }

    public Input criarInput(Input input) {
        return inputRepository.save(input);
    }

    public Input atualizarInput(Input input) {
        return inputRepository.save(input);
    }

    public void deletarInput(Long id) {
        inputRepository.deleteById(id);
    }
}