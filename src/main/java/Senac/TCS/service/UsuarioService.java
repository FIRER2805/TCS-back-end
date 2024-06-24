package Senac.TCS.service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.dto.CadastroDTO;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario inserir(CadastroDTO cadastroDTO) throws CampoInvalidoException {
        String hashSenha = new BCryptPasswordEncoder().encode(cadastroDTO.getSenha());
        Usuario usuario = Usuario.builder()
                .nome(cadastroDTO.getNome())
                .email(cadastroDTO.getEmail())
                .senha(hashSenha)
                .numero(cadastroDTO.getTelefone())
                .build();

        if(this.usuarioRepository.existsByEmail(cadastroDTO.getEmail())){
            throw new CampoInvalidoException("Email ja cadastrado");
        }

        if(!cadastroDTO.getSenha().equals(cadastroDTO.getSenhaConfirmada())){
            throw new CampoInvalidoException("Senhas não são iguais");
        }

        return this.usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }

    private boolean emailValido(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
