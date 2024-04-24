package Senac.TCS.model.dto;

import Senac.TCS.model.entity.Mensagem;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NodeGrafoDto {

    public NodeGrafoDto(Mensagem mensagem){
        this.id = mensagem.getId();
        this.label = mensagem.getConteudo();
        this.idSetor = mensagem.getIdSetor();
    }

    private Long id;
    private String label;
    private Long idSetor;
}
