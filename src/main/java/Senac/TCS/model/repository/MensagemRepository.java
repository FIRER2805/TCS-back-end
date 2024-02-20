package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MensagemRepository extends CrudRepository<Mensagem,Long> {

    @Query(value="select m.* from mensagem m\n" +
            "join setor s on\n" +
            "s.id = m.id_setor\n" +
            "join mensagem_input mi on\n" +
            "mi.id_mensagem_pai = m.id\n" +
            "where s.id = :idSetor \n" +
            "AND not exists(select 1 from mensagem_input mi2  where mi2.id_mensagem_filha = mi.id);",
    nativeQuery = true)
    Optional<Mensagem> obterMensagemRoot(@Param("idSetor") Long idSetor);

}
