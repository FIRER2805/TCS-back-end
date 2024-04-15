package Senac.TCS.api.repository;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemJdbcRepository;
import Senac.TCS.model.repository.MensagemRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MensagemRepositoryTest {
    @Autowired
    private MensagemRepository mensagemRepository;

    @Test
    @Order(1)
    public void mensagemRepository_salvarMensagemRaiz_retornarMensagemSalva(){

        Mensagem mensagemRaiz = Mensagem.builder().conteudo("Bem vindo!").build();

        Mensagem mensagemRaizRetornada = mensagemRepository.save(mensagemRaiz);

        Assertions.assertNotNull(mensagemRaizRetornada);
        Assertions.assertEquals(mensagemRaizRetornada.getId(), 1);

        System.out.println(mensagemRaizRetornada.getId());

    }

    @Test
    @Order(2)
    public void mensagemRepository_salvarMensagensFilhas_retornarMensagemSalva(){
        long idPai = 1;

        Mensagem mensagemFilha1 = Mensagem.builder()
                .inputPai("Quem é você?")
                .conteudo("sou o bot venom")
                .idMensagemPai(idPai)
                .build();
        Mensagem mensagemFilha2 = Mensagem.builder()
                .inputPai("Quem que criou você?")
                .conteudo("Meu criador é o Gabriel Henrique :)")
                .idMensagemPai(idPai)
                .build();
        Mensagem mensagemFilha3 = Mensagem.builder()
                .inputPai("Qual o seu propósito?")
                .conteudo("Eu fui criado para facilitar a sua vida ;)")
                .idMensagemPai(idPai)
                .build();

        Mensagem mensagemFilha1Retornada = mensagemRepository.save(mensagemFilha1);
        Mensagem mensagemFilha2Retornada = mensagemRepository.save(mensagemFilha2);
        Mensagem mensagemFilha3Retornada = mensagemRepository.save(mensagemFilha3);

        Assertions.assertNotNull(mensagemFilha1Retornada);
        Assertions.assertNotNull(mensagemFilha2Retornada);
        Assertions.assertNotNull(mensagemFilha3Retornada);

        Assertions.assertNotEquals(mensagemFilha1Retornada.getId(), 0);
        Assertions.assertNotEquals(mensagemFilha2Retornada.getId(), 0);
        Assertions.assertNotEquals(mensagemFilha3Retornada.getId(), 0);
    }
}
