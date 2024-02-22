package Senac.TCS.model.dto;

import Senac.TCS.model.entity.Mensagem;

public class mensagemDTO {
	
	private Long id;
	private String conteudo;
	private Long idSetor;
	private String inputPai;
	private Long idMensagemPai;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Long getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}
	public String getInputPai() {
		return inputPai;
	}
	public void setInputPai(String inputPai) {
		this.inputPai = inputPai;
	}
	public Long getIdMensagemPai() {
		return idMensagemPai;
	}
	public void setIdMensagemPai(Long idMensagemPai) {
		this.idMensagemPai = idMensagemPai;
	}
}
