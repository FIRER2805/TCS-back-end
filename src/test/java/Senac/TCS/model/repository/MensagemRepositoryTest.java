package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MensagemRepositoryTest {

    @Autowired
    MensagemRepository mensagemRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve obter a mensagem root de uma arvore de mensagem")
    void obterMensagemRootSucess() {
        long idSetor = 1;
        Optional<Mensagem> mensagem = mensagemRepository.obterMensagemRoot(idSetor);
        assertThat(mensagem.isPresent()).isTrue();
    }
}