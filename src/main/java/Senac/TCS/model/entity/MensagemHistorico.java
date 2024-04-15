package Senac.TCS.model.entity;

import Senac.TCS.model.dto.MensagemHistoricoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mensagem_historico")
public class MensagemHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conteudo", nullable = false, length = 255)
    private String conteudo;

	@Column(name = "data_envio")
	private LocalDateTime dataEnvio;

    @Column(name = "id_contato", nullable = false)
    private Long idContato;
}
