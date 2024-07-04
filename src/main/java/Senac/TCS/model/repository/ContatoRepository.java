package Senac.TCS.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Senac.TCS.model.entity.Contato;

import java.util.List;

public interface ContatoRepository extends CrudRepository<Contato, Long>{

    public Contato findByNumero(String numero);
    public Contato findByIdUsuario(Long idUsuario);
    @Query(value = " SELECT " +
            " c.* " +
            "    , max(mh.data_envio) " +
            " FROM contato c " +
            " JOIN mensagem_historico mh ON c.id = mh.id_contato " +
            " where c.id_usuario = ?1 " +
            " group by" +
            " c.id, " +
            " c.id_usuario " +
            " , c.nome " +
            "    , c.numero " +
            "ORDER BY 4 desc ", nativeQuery = true)
    List<Contato> findContatoByMostRecentMessage(Long idUsuario);
}
