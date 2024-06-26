package Senac.TCS.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contato")
public class Contato {

	public Contato(Long id, String nome, String numero) {
		super();
		this.id = id;
		this.nome = nome;
		this.numero = numero;
	}

	public Contato() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="id_usuario")
	private Long idUsuario;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "numero", unique = true, nullable = false)
	private String numero;

	@Column(name="automatizado")
	private boolean automatizado;
}
