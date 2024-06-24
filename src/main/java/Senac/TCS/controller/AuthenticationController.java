package Senac.TCS.controller;

import Senac.TCS.exception.CampoInvalidoException;
import Senac.TCS.model.dto.CadastroDTO;
import Senac.TCS.model.dto.LoginDTO;
import Senac.TCS.model.dto.TokenDTO;
import Senac.TCS.model.entity.Usuario;
import Senac.TCS.service.TokenService;
import Senac.TCS.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:9000"}, maxAge = 3600)
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);

        String token = tokenService.gerarTokenJWT((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody CadastroDTO cadastroDTO){
        try {
            usuarioService.inserir(cadastroDTO);
            return ResponseEntity.ok().build();
        } catch (CampoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

