package Senac.TCS.service;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.dto.CadastroDTO;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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

	public List<Usuario> listarUsuariosPorSetor(Long idSetor) {
		return usuarioRepository.findUsuariosByIdSetor(idSetor);
	}

    public Usuario inserir(CadastroDTO cadastroDTO) throws CampoInvalidoException {
        String hashSenha = new BCryptPasswordEncoder().encode(cadastroDTO.getSenha());
        Usuario usuario = Usuario.builder()
                .nome(cadastroDTO.getNome())
                .email(cadastroDTO.getEmail())
                .senha(hashSenha)
                .telefone(cadastroDTO.getTelefone())
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

	public boolean excluirUsuarioSetor(Long idUsuario, Long idSetor) {
		try {
			usuarioRepository.removerUsuarioDoSetor(idUsuario, idSetor );
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	public void inserirUsuarioNoSetor(Long idUsuario, Long idSetor, boolean administrador) {
		usuarioRepository.inserirUsuarioNoSetor(idUsuario, idSetor, administrador);
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

	public Usuario atualizarUsuario(Usuario usuario) throws CampoInvalidoException {

		validarAtributosDeUsuario(usuario);

		return usuarioRepository.save(usuario);
	}

	public void validarAtributosDeUsuario(Usuario usuario) throws CampoInvalidoException {

		String mensagem = "";
		if (validarNome(usuario.getNome())) {
			mensagem += "\nNome invalido";
		}
		if (validarEmail(usuario.getEmail())) {
			mensagem += "\nEmail invalido";
		}
		if (validarSenha(usuario.getSenha())) {
			mensagem += "\nSenha invalido";
		}

		if (mensagem.isBlank()) {
			throw new CampoInvalidoException(mensagem);
		}

	}

	private boolean validarNome(String nome) {
		return nome.matches("X{3,}");
	}

	private boolean validarEmail(String email) {
		return email.matches("[\\w.-]+@gmail.com$");
	}

	private boolean validarSenha(String senha) {
		return senha.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$");
	}

	public String recuperarSenha(String email) {
		SecureRandom gerador = new SecureRandom();
		StringBuilder senha = new StringBuilder();

		String Letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		for (int i = 0; i < 8; i++) {
			int indiceAleatorio = gerador.nextInt(Letras.length());
			senha.append(Letras.charAt(indiceAleatorio));
		}

		return senha.toString();
	}
}
