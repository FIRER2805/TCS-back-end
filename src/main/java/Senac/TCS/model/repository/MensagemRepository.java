package Senac.TCS.model.repository;

import org.springframework.data.repository.CrudRepository;

import Senac.TCS.model.entity.Mensagem;

public interface MensagemRepository extends CrudRepository<Mensagem, Long> {
	/*
	 * @Query(value="select m.* from mensagem m \n" + "join setor s on \n" +
	 * "s.id = m.id_setor \n" + "where s.id = :idSetor \n" +
	 * "AND not exists(select 1 from mensagem_input mi  where mi.id_mensagem_filha = m.id);"
	 * , nativeQuery = true) Optional<Mensagem> obterMensagemRoot(@Param("idSetor")
	 * Long idSetor);
	 */

}
