package entities;

public class Setor {
	
	private Integer id;
	private String nome;
	private String descricao;
	private int usuarios;
	
	
	public Setor() {
		
	}
	public Setor(Integer idSetor, String nome, String descricao, int usuarios) {
		super();
		this.id = idSetor;
		this.nome = nome;
		this.descricao = descricao;
		this.usuarios = usuarios;
	}
	
	public Integer getIdSetor() {
		return id;
	}
	public void setIdSetor(Integer idSetor) {
		this.id = idSetor;
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
	public int getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(int usuarios) {
		this.usuarios = usuarios;
	}

}
