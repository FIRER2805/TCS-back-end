package Senac.TCS.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;
    @OneToMany(mappedBy = "mensagemAnterior")
    private List<Input> inputsAnteriores;

    @OneToMany(mappedBy = "mensagemSucessora")
    private List<Input> inputsSucessores;
}
