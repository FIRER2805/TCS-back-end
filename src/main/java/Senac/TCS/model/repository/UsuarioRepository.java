package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    public UserDetails findByEmail(String email);
    public boolean existsByEmail(String email);
    boolean existsBySenha(String senha);


    @Query(value = " select u.* from usuario u "
            + " join usuario_setor us on "
            + " us.id_usuario = u.id "
            + " join setor s on "
            + " s.id = us.id_setor "
            + " where s.id = ? " ,nativeQuery  = true)
    List<Usuario> findUsuariosByIdSetor(Long idSetor);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario_setor (id_usuario, id_setor, administrador) VALUES (?, ?, ?)", nativeQuery = true)
    void inserirUsuarioNoSetor(Long idUsuario, Long idSetor, boolean administrador);

    //QUERY PARA EXCLUIR USUARIO SETOR
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario_setor WHERE id_usuario = :idUsuario AND id_setor = :idSetor", nativeQuery = true)
    void removerUsuarioDoSetor(@Param("idUsuario") Long idUsuario, @Param("idSetor") Long idSetor);
}
