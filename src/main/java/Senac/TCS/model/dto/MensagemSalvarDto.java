package Senac.TCS.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensagemSalvarDto {
    private String conteudo;
    private Long idSetor;
    private InputSalvarDto inputPai;
}
