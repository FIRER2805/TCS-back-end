package Senac.TCS.graphMensagem;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.service.MensagemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class Mesagem {

    @Autowired
    MensagemService mensagemService;

    @Test
    public void obterMensagemRoot(){
        Optional<Mensagem> mensagemRetornada = mensagemService.obterMensagemRoot(1L);

        assertThat(mensagemRetornada.isPresent()).isTrue();
    }
}
