package Senac.TCS.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Senac.TCS.model.entity.MensagemHistorico;

public interface MensagemHistoricoRepository extends CrudRepository<MensagemHistorico, Long> {
	public List<MensagemHistorico> findByIdContato(Long idContato);
}
