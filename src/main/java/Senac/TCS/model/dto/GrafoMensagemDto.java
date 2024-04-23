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
public class GrafoMensagemDto {
    private List<Mensagem> mensagens;
    private List<Input> inputs;
}
