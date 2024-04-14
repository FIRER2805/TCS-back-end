package Senac.TCS.api.repository;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemJdbcRepository;
import Senac.TCS.model.repository.MensagemRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MensagemJdbcRepositoryTest {

    @Autowired
    public MensagemRepository mensagemRepository;

    @Autowired
    MensagemJdbcRepository mensagemJdbcRepository;

    @Test
    @Order(1)
    public void buscarTodasMensagens(){
        Mensagem mensagem = Mensagem.builder()
                .conteudo("teste").build();

        mensagemRepository.save(mensagem);

        List<Mensagem> mensagens = mensagemJdbcRepository.obterTodas();

        Assertions.assertFalse(mensagens.isEmpty());
    }

}
