package Senac.TCS.seletor;

public class SetorSeletor extends BaseSeletor {

	private String nome;
	private String descricao;

	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
				|| (this.descricao != null && this.descricao.trim().length() > 0);

	}

	public SetorSeletor() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
