package Senac.TCS.service;

import Senac.TCS.model.entity.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public Usuario retornarUsuarioRequisicao(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Usuario) userDetails;
    }
}
