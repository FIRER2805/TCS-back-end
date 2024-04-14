package Senac.TCS.model.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MensagemDto {

    private Long id;
    private String conteudo;
    private Long idSetor;
    private String inputPai;
    private List<MensagemDto> mensagensFilhas;
}
