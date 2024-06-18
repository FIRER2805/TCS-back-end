package Senac.TCS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
	 		+ " where s.id = ?1 " ,nativeQuery  = true)
	    List<Usuario> findUsuariosByIdSetor(Long idSetor);
}
