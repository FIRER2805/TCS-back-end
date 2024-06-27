package Senac.TCS.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import Senac.TCS.model.dto.SetorDTO;

@Data
@Entity
@Table(name = "setor")
public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idSetor;
	private String nome;
	private String descricao;
	
	
	public Setor() {
		super();
	}
	public Setor(Long idSetor, String nome, String descricao) {
		super();
		this.idSetor = idSetor;
		this.nome = nome;
		this.descricao = descricao;
		
	}
	
	// Construtor que aceita um SetorDTO
    public Setor(SetorDTO setorDTO) {
        this.nome = setorDTO.getNome();
        this.descricao = setorDTO.getDescricao();
    }


}
