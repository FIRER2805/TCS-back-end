package Senac.TCS.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphMensagem {
    private Mensagem root = new Mensagem();

    public void adicionarInputNaMensagemRoot(String conteudo){
        Input input = new Input(conteudo, this.getRoot());
        this.getRoot().getInputs().add(input);
    }
}
