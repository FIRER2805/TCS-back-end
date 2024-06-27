package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>, JpaSpecificationExecutor<Setor> {
    @Query(value = " select s.* from setor s " +
            " join usuario_setor us on " +
            " s.id = us.id_setor " +
            " where us.id_usuario = ?1 ", nativeQuery = true)
    public List<Setor> obterSetoresDeUsuario(Long idUsuario);
}
