package Senac.TCS.model.repository;

import Senac.TCS.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    public UserDetails findByEmail(String email);
    public boolean existsByEmail(String email);
}
