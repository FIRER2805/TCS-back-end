package Senac.TCS.model.dto;

import Senac.TCS.model.entity.Input;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EdgeGrafoDto {

    public EdgeGrafoDto(Input input){
        this.id = input.getId();
        this.label = input.getConteudo();
        this.source = input.getIdMensagemPai();
        this.target = input.getIdMensagemFilha();
    }

    private Long id;
    private String label;
    private Long source;
    private Long target;
}
