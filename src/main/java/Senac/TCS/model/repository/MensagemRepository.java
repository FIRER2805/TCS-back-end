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
	public boolean existsByInputPai(String input);

	@Query(value="select m.input_pai " +
			"from mensagem m " +
			"where id_setor = ?1 AND id_mensagem_pai = ?2 ; "
			, nativeQuery = true)
	public List<String> obterInputsValidos(Long idSetor, Long idMensagemPai);
}
