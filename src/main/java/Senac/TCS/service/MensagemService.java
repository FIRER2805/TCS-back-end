package Senac.TCS.service;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> listarTodasMensagens() {
        return (List<Mensagem>) mensagemRepository.findAll();
    }

    public Mensagem obterMensagemPorId(Long id) {
        return mensagemRepository.findById(id).orElse(null);
    }

    public Mensagem obterMensagemRoot(Long idSetor){
        return mensagemRepository.obterMensagemRoot(idSetor).orElse(null);
    }

    public Mensagem criarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public Mensagem atualizarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public void deletarMensagem(Long id) {
        mensagemRepository.deleteById(id);
    }
}
