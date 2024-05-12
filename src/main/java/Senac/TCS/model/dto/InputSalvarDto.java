package Senac.TCS.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputSalvarDto {
    private String conteudo;
    private Long idMensagemPai;
    private Long idMensagemFilha;
}
