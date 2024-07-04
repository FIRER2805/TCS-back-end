package Senac.TCS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenDTO {
    private Long idUsuario;
    private String token;
}
