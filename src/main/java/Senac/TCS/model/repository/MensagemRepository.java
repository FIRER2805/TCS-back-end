package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import org.springframework.data.repository.CrudRepository;

public interface MensagemRepository extends CrudRepository<Mensagem,Long> {
}
