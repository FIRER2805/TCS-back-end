package Senac.TCS.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemDTO {
	
	private Long id;
	private String conteudo;
	private Long idSetor;
	private Long idUsuario;
	private String numeroContato;
	private String inputPai;
	private Long idMensagemPai;
}
