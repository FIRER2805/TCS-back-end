package Senac.TCS.model.entity;
import lombok.Data;

import Senac.TCS.model.dto.SetorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
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
	
	// Construtor que aceita um SetorDTO
    public Setor(SetorDTO setorDTO) {
        this.nome = setorDTO.getNome();
        this.descricao = setorDTO.getDescricao();
    }


}
