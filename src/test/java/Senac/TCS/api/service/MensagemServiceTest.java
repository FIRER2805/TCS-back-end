package Senac.TCS.api.service;

import Senac.TCS.exception.MensagemInvalidaException;
import Senac.TCS.model.dto.GrafoMensagemDto;
import Senac.TCS.model.dto.InputSalvarDto;
import Senac.TCS.model.dto.MensagemDto;
import Senac.TCS.model.dto.MensagemSalvarDto;
import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.service.InputService;
import Senac.TCS.service.MensagemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MensagemServiceTest {
    @Autowired
    public MensagemService mensagemService;

    @Autowired
    public InputService inputService;

    @Test
    @Order(1)
    public void mensagemService_salvarMensagemRaiz_retornarMensagemSalva() throws MensagemInvalidaException {

        MensagemSalvarDto mensagemSalvar = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("bem-vindo")
                .build();

        Mensagem mensagemRaizRetornada = mensagemService.criarMensagem(mensagemSalvar);

        Assertions.assertNotNull(mensagemRaizRetornada);
        Assertions.assertEquals(mensagemRaizRetornada.getId(), 1);

    }

    @Test
    @Order(2)
    public void mensagemService_salvarArvore_retornarMensagensEOpcoesSalvas() throws MensagemInvalidaException {
        MensagemSalvarDto mensagemRaiz = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Raiz")
                .build();
        MensagemSalvarDto mensagemFilha1 = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Filha1")
                .build();
        MensagemSalvarDto mensagemFilha2 = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("filha2")
                .build();

        Mensagem mensagemRaizRetornada = mensagemService.criarMensagem(mensagemRaiz);
        Mensagem mensagemFilhaRetornada1 = mensagemService.criarMensagem(mensagemFilha1);
        Mensagem mensagemFilhaRetornada2 = mensagemService.criarMensagem(mensagemFilha2);

        Assertions.assertNotNull(mensagemRaizRetornada);
        Assertions.assertEquals(mensagemRaizRetornada.getId(), 2);
        Assertions.assertNotNull(mensagemFilhaRetornada1);
        Assertions.assertEquals(mensagemFilhaRetornada1.getId(), 3);
        Assertions.assertNotNull(mensagemFilhaRetornada2);
        Assertions.assertEquals(mensagemFilhaRetornada2.getId(), 4);

        InputSalvarDto input1 = InputSalvarDto.builder()
                .idMensagemPai(mensagemRaizRetornada.getId())
                .idMensagemFilha(mensagemFilhaRetornada1.getId())
                .conteudo("Opção1")
                .build();
        InputSalvarDto input2 = InputSalvarDto.builder()
                .idMensagemPai(mensagemRaizRetornada.getId())
                .idMensagemFilha(mensagemFilhaRetornada2.getId())
                .conteudo("Opção2")
                .build();

        Input inputRetornado1 = inputService.salvarInput(mensagemFilhaRetornada1, input1);
        Input inputRetornado2 = inputService.salvarInput(mensagemFilhaRetornada2, input2);

        Assertions.assertNotNull(inputRetornado1);
        Assertions.assertEquals(inputRetornado1.getId(), 1);
        Assertions.assertNotNull(inputRetornado2);
        Assertions.assertEquals(inputRetornado2.getId(), 2);
    }

    @Test
    @Order(3)
    public void mensagemService_AtualizarMensagem_retornarMensagemAtualizada() throws MensagemInvalidaException {
        MensagemSalvarDto mensagemSalvar = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Teste")
                .build();

        Mensagem mensagemRetornada = mensagemService.criarMensagem(mensagemSalvar);
        Long idMensagemInserida = mensagemRetornada.getId();

        Mensagem mensagemEditar = Mensagem.builder()
                .id(idMensagemInserida)
                .idSetor(1l)
                .conteudo("Editou")
                .build();

        Mensagem mensagemEditada = mensagemService.atualizarMensagem(mensagemEditar);

        Assertions.assertEquals(mensagemEditada.getConteudo(), "Editou");
    }

    @Test
    @Order(4)
    public void mensagemService_deletarMensagem() throws MensagemInvalidaException {
        MensagemSalvarDto mensagemSalvar = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Mensagem a ser deletada")
                .build();

        Mensagem mensagemRetornada = mensagemService.criarMensagem(mensagemSalvar);
        long idMensagemRetornada = mensagemRetornada.getId();

        mensagemService.deletarMensagem(mensagemRetornada);

        Mensagem mensagemEncontrada = mensagemService.obterMensagemPorId(idMensagemRetornada);

        Assertions.assertNull(mensagemEncontrada);
    }

    @Test
    @Order(5)
    public void mensagemService_buscarGrafo_retornaGrafo() throws MensagemInvalidaException {
        MensagemSalvarDto mensagemRaiz = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Raiz")
                .build();
        MensagemSalvarDto mensagemFilha1 = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("Filha1")
                .build();
        MensagemSalvarDto mensagemFilha2 = MensagemSalvarDto.builder()
                .idSetor(1l)
                .conteudo("filha2")
                .build();

        Mensagem mensagemRaizRetornada = mensagemService.criarMensagem(mensagemRaiz);
        Mensagem mensagemFilhaRetornada1 = mensagemService.criarMensagem(mensagemFilha1);
        Mensagem mensagemFilhaRetornada2 = mensagemService.criarMensagem(mensagemFilha2);

        InputSalvarDto input1 = InputSalvarDto.builder()
                .idMensagemPai(mensagemRaizRetornada.getId())
                .idMensagemFilha(mensagemFilhaRetornada1.getId())
                .conteudo("Opção1")
                .build();
        InputSalvarDto input2 = InputSalvarDto.builder()
                .idMensagemPai(mensagemRaizRetornada.getId())
                .idMensagemFilha(mensagemFilhaRetornada2.getId())
                .conteudo("Opção2")
                .build();

        Input inputRetornado1 = inputService.salvarInput(mensagemFilhaRetornada1, input1);
        Input inputRetornado2 = inputService.salvarInput(mensagemFilhaRetornada2, input2);

        GrafoMensagemDto grafo = mensagemService.obterGrafoMensagem(1l);

        Assertions.assertFalse(grafo.getNodes().isEmpty());
        Assertions.assertFalse(grafo.getEdges().isEmpty());
    }
}
