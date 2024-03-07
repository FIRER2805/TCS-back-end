package Senac.TCS.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Senac.TCS.model.entity.MensagemHistorico;

@Repository
public interface MensagemHistoricoRepository extends CrudRepository<MensagemHistorico, Long> {
	
	@Query(value="select mh.data_envio "
			+ "from mensagem_historico mh "
			+ "join contato c on "
			+ "c.id = mh.id_contato "
			+ "join usuario u on "
			+ "u.id = c.id_usuario "
			+ "where mh.id_contato = 1? "
			+ "AND u.id = 2? "
			+ "order by mh.data_envio desc "
			+ "limit 1;", nativeQuery = true)
	public LocalDateTime obterDataUltimaMensagem(Long idContato, Long idUsuario);
	
	public List<MensagemHistorico> findByIdContato(Long idContato);
}
