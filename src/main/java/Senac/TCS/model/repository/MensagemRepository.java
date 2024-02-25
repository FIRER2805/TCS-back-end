package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MensagemRepository extends CrudRepository<Mensagem,Long> {
/*
    @Query(value="select m.* from mensagem m \n" +
            "join setor s on \n" +
            "s.id = m.id_setor \n" +
            "where s.id = :idSetor \n" +
            "AND not exists(select 1 from mensagem_input mi  where mi.id_mensagem_filha = m.id);",
    nativeQuery = true)
    Optional<Mensagem> obterMensagemRoot(@Param("idSetor") Long idSetor);
*/
}
