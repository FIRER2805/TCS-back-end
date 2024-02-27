package Senac.TCS.model.dto;

public class ContatoDTO {
	
	private Long id;
	private Long idUsuario;
	private String nome;
	@jakarta.validation.constraints.Pattern(regexp = "^\\d{2}\\d{9}$", message = "O número de telefone deve ter 11 dígitos.")
	private String numero;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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
}
