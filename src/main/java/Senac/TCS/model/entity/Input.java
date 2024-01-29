package Senac.TCS.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="input")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_mensagem_anterior")
    private Mensagem mensagemAnterior;

    @ManyToOne
    @JoinColumn(name = "id_mensagem_sucessora")
    private Mensagem mensagemSucessora;
}
