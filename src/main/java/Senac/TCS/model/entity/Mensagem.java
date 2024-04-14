package Senac.TCS.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mensagem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "id_setor")
    private Long idSetor;
    @Column(name = "input_pai")
    private String inputPai;
    @Column(name = "id_mensagem_pai")
    private Long idMensagemPai;
}
