package Senac.TCS.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Input {
    Input(String conteudo, Mensagem mensagemAnterior){
        this.conteudo = conteudo;
        this.mensagemAnterior = mensagemAnterior;
    }

    private Long id;
    private String conteudo;
    private Mensagem mensagemAnterior;
    private Mensagem mensagemSucessora;
}
