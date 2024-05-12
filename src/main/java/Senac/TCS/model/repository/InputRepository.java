package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Input;
import Senac.TCS.model.entity.Mensagem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputRepository extends CrudRepository<Input,Long> {
    @Query(value = " select i.* " +
            " from input i " +
            " join mensagem m on " +
            " m.id = i.id_mensagem_pai " +
            " where m.id_setor = ?1 ; ",
            nativeQuery = true)
    public List<Input> obterInputsPorIdSetor(Long idSetor);

    @Query(value = " select i.* from input i " +
            " join mensagem m on " +
            " m.id = i.id_mensagem_pai " +
            " where m.id = ?1 AND id_setor = ?2 ; ", nativeQuery = true)
    public List<Input> obterInputsValidosDeMensagem(Long idMensagemPai, Long idSetor);

    public Input findByIdMensagemPaiAndIdMensagemFilha(Long idMensagemPai, Long idMensagemFilha);

    @Transactional
    public void deleteByIdMensagemFilha(Long idMensagemFilha);
}
