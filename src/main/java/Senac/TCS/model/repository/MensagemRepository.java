package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MensagemRepository extends CrudRepository<Mensagem,Long>, JpaSpecificationExecutor<Mensagem> {
//	@Query(value="select * from mensagem " +
//            "where id_setor = :idSetor AND id_mensagem_pai is null ;"
//            , nativeQuery = true)
//    public Mensagem obterMensagemRoot(@Param("idSetor") Long idSetor);
//
//    @Query(value="select input_pai from mensagem " +
//            "where id_mensagem_pai = :idMensagemPai AND id_setor = :idSetor ;"
//            , nativeQuery = true)
//    public List<String> obterInputsFilhos(@Param("idMensagemPai") Long idMensagemPai, @Param("idSetor") Long idSetor);
//
//    @Query(value="select * from mensagem " +
//            "where id_mensagem_pai = :idMensagemPai AND id_setor = :idSetor " +
//            "AND input_pai LIKE :inputPai ;"
//            , nativeQuery = true)
//    public Mensagem obterMensagemFilhaPorInputPai(@Param("idMensagemPai") Long idMensagemPai
//            , @Param("idSetor") Long idSetor, @Param("inputPai") String inputPai);
}
