package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends CrudRepository<Mensagem,Long> {
	public List<Mensagem> findByIdSetor(Long idSetor);

	@Query(value = " select m.* from mensagem m " +
			" join input i on " +
			" i.id_mensagem_pai = m.id " +
			" where not exists( " +
			" select 1 " +
			"    from input i2 " +
			" where i2.id_mensagem_filha = m.id) " +
			" AND id_setor = ?1 ; ", nativeQuery = true)
	public Mensagem obterMensagemRoot(Long idSetor);

	@Query(value = " select m.* from mensagem m " +
			" join input i on " +
			" i.id_mensagem_filha = m.id " +
			" where i.conteudo like ?1 " +
			" AND i.id_mensagem_pai = ?2 " +
			" AND id_setor = ?3 ; ", nativeQuery = true)
	public Mensagem obterMensagemFilha(String conteudo, Long idMensagemPai, Long idSetor);

}
