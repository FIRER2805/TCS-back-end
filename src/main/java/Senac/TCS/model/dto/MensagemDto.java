package Senac.TCS.model.dto;

import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MensagemDto {

    public MensagemDto(Mensagem mensagem, List<Input> inputsFilhos){
        this.id = mensagem.getId();
        this.conteudo = mensagem.getConteudo();
        this.idSetor = mensagem.getIdSetor();
        this.inputsFilhos = inputsFilhos;
    }

    private Long id;
    private String conteudo;
    private Long idSetor;
    private List<Input> inputsFilhos;
}
