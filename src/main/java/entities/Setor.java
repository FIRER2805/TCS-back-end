package entities;

import java.util.ArrayList;

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
	private Integer id;
	private String nome;
	private String descricao;
	private ArrayList<Integer> usuarios;
	
	
	public Setor() {
		super();
	}
	public Setor(Integer idSetor, String nome, String descricao, ArrayList<Integer> usuarios) {
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
	public ArrayList<Integer> getUsuarios() {
		return this.usuarios;
	}
	public void setUsuarios(ArrayList<Integer> usuarios) {
		this.usuarios = usuarios;
	}

}
