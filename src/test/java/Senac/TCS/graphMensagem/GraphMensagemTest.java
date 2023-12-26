package Senac.TCS.graphMensagem;

import Senac.TCS.model.entity.GraphMensagem;
import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphMensagemTest {
    @Test
    public void testAdicionarInputNaMensagemRoot() {
        GraphMensagem graphMensagem = new GraphMensagem();

        String conteudoInput = "Conteúdo do input";
        graphMensagem.adicionarInputNaMensagemRoot(conteudoInput);

        Mensagem root = graphMensagem.getRoot();
        assertNotNull(root);
        assertNotNull(root.getInputs());
        assertFalse(root.getInputs().isEmpty());

        Input inputAdicionado = root.getInputs().get(0);
        assertNotNull(inputAdicionado);
        assertEquals(conteudoInput, inputAdicionado.getConteudo());
        assertEquals(root, inputAdicionado.getMensagemAnterior());
        assertNull(inputAdicionado.getMensagemSucessora());
    }
}

