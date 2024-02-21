package Senac.TCS.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;
    @Column(name = "input_pai")
    private String inputPai;

    @OneToMany(mappedBy = "mensagemPai")
    private List<Mensagem> mensagensFilhas;

    @ManyToOne
    @JoinColumn(name = "id_mensagem_pai")
    @JsonBackReference
    private Mensagem mensagemPai;
}
