package Senac.TCS.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemHistoricoDto {
    String conteudo;
    String numeroContato;
    
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getNumeroContato() {
		return numeroContato;
	}
	public void setNumeroContato(String numeroContato) {
		this.numeroContato = numeroContato;
	}
}
