package Senac.TCS.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="input")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;
    @JoinTable(
            name="mensagem_input",
            joinColumns = @JoinColumn(name="id_mensagem_pai"),
            inverseJoinColumns = @JoinColumn(name="id_input")
    )
    @ManyToOne
    @JsonBackReference
    private Mensagem mensagensFilha;
}
