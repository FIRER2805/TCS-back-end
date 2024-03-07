package Senac.TCS.model.seletor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemSeletor {
	private Long idSetor;
	private Long idMensagemPai;
	private String inputPai;
	
	public Long getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}
	public Long getIdMensagemPai() {
		return idMensagemPai;
	}
	public void setIdMensagemPai(Long idMensagemPai) {
		this.idMensagemPai = idMensagemPai;
	}
	public String getInputPai() {
		return inputPai;
	}
	public void setInputPai(String inputPai) {
		this.inputPai = inputPai;
	}
}
