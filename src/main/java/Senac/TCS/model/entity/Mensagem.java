package Senac.TCS.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Senac.TCS.model.DTO.mensagemDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mensagem {
	
    public Mensagem() {
		super();
	}
    
	public Mensagem(mensagemDTO dto) {
        this.id = dto.getId();
        this.conteudo = dto.getConteudo();
        this.idSetor = dto.getIdSetor();
        this.inputPai = dto.getInputPai();
        this.mensagemPai = new Mensagem();
        this.mensagemPai.setId(dto.getId());
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "conteudo")
    private String conteudo;
    @Column(name = "id_setor")
    private Long idSetor;
    @Column(name = "input_pai")
    private String inputPai;

    @OneToMany(mappedBy = "mensagemPai")
    private List<Mensagem> mensagensFilhas;
    
    @ManyToOne
    @JoinColumn(name = "id_mensagem_pai")
    @JsonBackReference
    private Mensagem mensagemPai;

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

	public List<Mensagem> getMensagensFilhas() {
		return mensagensFilhas;
	}

	public void setMensagensFilhas(List<Mensagem> mensagensFilhas) {
		this.mensagensFilhas = mensagensFilhas;
	}

	public Mensagem getMensagemPai() {
		return mensagemPai;
	}

	public void setMensagemPai(Mensagem mensagemPai) {
		this.mensagemPai = mensagemPai;
	}
}