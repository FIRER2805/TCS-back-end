package Senac.TCS.model.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MensagemDto {

    private Long key;
    private String label;
    private Long idSetor;
    private String inputPai;
    private List<MensagemDto> children;
}
