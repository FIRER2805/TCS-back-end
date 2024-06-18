package Senac.TCS.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "setor")
public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	
	
	public Setor() {
		super();
	}
	public Setor(Long idSetor, String nome, String descricao) {
		super();
		this.id = idSetor;
		this.nome = nome;
		this.descricao = descricao;
		
	}
	
	public Long getIdSetor() {
		return id;
	}
	public void setIdSetor(Long idSetor) {
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


}
