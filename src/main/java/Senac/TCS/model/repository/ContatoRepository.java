package Senac.TCS.model.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import Senac.TCS.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	Long findIdByNumero(@Param("numero") String numero);
    List<Contato> findContatosByUsuario(long id_usuario);
    boolean existsByNumero(String numero);
    
    @Query("SELECT c FROM Contato c JOIN Mensagem m ON c.id = m.id_contato "
			+ "WHERE m.data_envio = (SELECT MAX(mh.data_envio) FROM MensagemHistorico mh WHERE mh.id_contato = c.id)")
	List<Contato> findContatoByMensagemMaisRecente();
}
