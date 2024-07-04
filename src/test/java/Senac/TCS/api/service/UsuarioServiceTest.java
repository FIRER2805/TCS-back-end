package Senac.TCS.api.service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.dto.CadastroDTO;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Order(1)
    public void usuarioService_cadastrarUsuario_retornarUsuarioCadastrado() throws CampoInvalidoException {
        CadastroDTO dadosCadastrais = new CadastroDTO();
        dadosCadastrais.setNome("Gabriel");
        dadosCadastrais.setSenha("admin123");
        dadosCadastrais.setSenhaConfirmada("admin123");
        dadosCadastrais.setEmail("gabriel@hotmail.com");
        dadosCadastrais.setTelefone("999999999");

        Usuario usuarioCadastrado = this.usuarioService.inserir(dadosCadastrais);

        Assertions.assertNotNull(usuarioCadastrado);
        Assertions.assertNotNull(usuarioCadastrado.getId());
    }
}
