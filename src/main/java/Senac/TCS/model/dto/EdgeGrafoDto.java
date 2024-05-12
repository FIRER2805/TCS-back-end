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
        this.label = input.getConteudo();
        this.source = String.valueOf(input.getIdMensagemPai());
        this.target = String.valueOf(input.getIdMensagemFilha());
    }

    private String label;
    private String source;
    private String target;
}
