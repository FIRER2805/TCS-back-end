package Senac.TCS.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import Senac.TCS.model.dto.RelatorioOpcaoUsos;
import Senac.TCS.model.dto.RelatorioOpcaoUsosContatos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Senac.TCS.model.entity.MensagemHistorico;

@Repository
public interface MensagemHistoricoRepository extends CrudRepository<MensagemHistorico, Long> {
	
	@Query(value="select mh.data_envio "
			+ "from mensagem_historico mh "
			+ "join contato c on "
			+ "c.id = mh.id_contato "
			+ "join usuario u on "
			+ "u.id = c.id_usuario "
			+ "where mh.id_contato = ?1 "
			+ "AND u.id = ?2 "
			+ "order by mh.data_envio desc "
			+ "limit 1;", nativeQuery = true)
	public LocalDateTime obterDataUltimaMensagem(Long idContato, Long idUsuario);
	
	public List<MensagemHistorico> findByIdContatoOrderByDataEnvioDesc(Long idContato);

	@Query(value=" select " +
			" mh.conteudo as \"opcao\", " +
			"    count(mh.conteudo) as \"usos\" " +
			" from mensagem_historico mh " +
			" join contato c on " +
			" c.id =  mh.id_contato " +
			" join usuario u on " +
			" u.id = c.id_usuario " +
			" where u.id = ?1 " +
			" group by mh.conteudo ", nativeQuery = true)
	public List<RelatorioOpcaoUsos> buscarDadosRelatorioOpcaoUsos(Long idUsuario);

	@Query(value=" select " +
			" mh.conteudo as \"opcao\", " +
			"    coalesce(c.nome ,c.numero) as \"contato\", " +
			"    count(mh.conteudo) as \"usos\" " +
			" from mensagem_historico mh " +
			" join contato c on " +
			" c.id =  mh.id_contato " +
			" join usuario u on " +
			" u.id = c.id_usuario " +
			" where u.id = ?1 " +
			" group by c.nome, c.numero, mh.conteudo, \"usos\" ; ", nativeQuery = true)
	public List<RelatorioOpcaoUsosContatos> buscarDadosRelatorioOpcaoUsosContato(Long idUsuario);
}
