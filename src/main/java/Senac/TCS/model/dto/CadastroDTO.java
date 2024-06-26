package Senac.TCS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private String senhaConfirmada;
    private String telefone;
}
