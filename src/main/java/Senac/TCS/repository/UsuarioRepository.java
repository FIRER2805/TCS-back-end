package Senac.TCS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Senac.TCS.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);
	boolean existsBySenha(String senha);
	Usuario findByEmail(String email);
	
	
	
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
	    
	  //QUERY PARA VALIDAR USUARIO SETOR
	    @Modifying
	    @Transactional
	    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM usuario_setor WHERE id_usuario = :idUsuario AND id_setor = :idSetor", nativeQuery = true)
	    int existsByUsuarioIdAndSetorId(@Param("idUsuario") Long idUsuario, @Param("idSetor") Long idSetor);
}
	
