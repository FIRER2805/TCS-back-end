package Senac.TCS.model.entity;

import Senac.TCS.model.DTO.ContatoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {

	public Contato(ContatoDTO dto) {
		super();
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.numero = dto.getNumero();
		this.automatizado = dto.getAutomatizado();
		this.usuario = dto.getIdUsuario();
	}

	public Contato() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_usuario")
	private Long usuario;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "numero", unique = true, nullable = false)
	private String numero;

	@Column(name = "automatizado")
	private int automatizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getAutomatizado() {
		return automatizado;
	}

	public void setAutomatizado(int automatizado) {
		this.automatizado = automatizado;
	}


}
