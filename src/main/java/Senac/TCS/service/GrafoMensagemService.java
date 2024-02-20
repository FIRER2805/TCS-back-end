package Senac.TCS.service;

import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GrafoMensagemService {

    @Autowired
    MensagemService mensagemService;

    public List<Mensagem> buscaMensagem(){
        List<Mensagem> mensagens = mensagemService.listarTodasMensagens();
        for(Mensagem m : mensagens){
            List<Input> inputs = m.getInputsSucessores();
            for(Input i : inputs){
                
            }
        }
        return null;
    }
}
