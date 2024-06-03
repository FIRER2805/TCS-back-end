package Senac.TCS.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Senac.TCS.model.entity.Contato;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface ContatoRepository extends CrudRepository<Contato, Long> {

	Long findIdByNumero(@Param("numero") String numero);
    List<Contato> findContatosByUsuario(long id_usuario);
    boolean existsByNumero(String numero);
    
    @Query(value = " SELECT c.* "
    		+ " FROM contato c "
    		+ " LEFT JOIN mensagem_historico mh ON c.id = mh.id_contato "
    		+ " ORDER BY mh.data_envio DESC ", nativeQuery = true)
    	List<Contato> findContatoByMostRecentMessage();

}
