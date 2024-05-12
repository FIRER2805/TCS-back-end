package Senac.TCS.service;

import Senac.TCS.model.dto.InputSalvarDto;
import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    public Input salvarInput(Mensagem mensagemFilha, InputSalvarDto input){
        Input novoInput = Input.builder()
                .conteudo(input.getConteudo())
                .idMensagemPai(input.getIdMensagemPai())
                .idMensagemFilha(mensagemFilha.getId())
                .build();
        return inputRepository.save(novoInput);
    }

    public Input atualizarInput(Input i){
        Input input = inputRepository.findByIdMensagemPaiAndIdMensagemFilha(i.getIdMensagemPai(), i.getIdMensagemFilha());
        i.setId(input.getId());
        return inputRepository.save(i);
    }
}
