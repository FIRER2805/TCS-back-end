package Senac.TCS.service;

import Senac.TCS.model.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String salt = "gMteI=aF:QL6v(PI3W73u9m.pJ:_8B+gOi8YS7h)1-Qhr4wr:#@(dIH3Y!#iDaQM:naj-311Ge-lWE=vR=oXCzVF+zFZXp1/xdRQ";

    public String gerarTokenJWT(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(salt);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.expiracao())
                    .sign(algorithm);
            System.out.println("token gerado:" + token);
            return token;
        }
        catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao criar o token", exception);
        }
    }

    private Instant expiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(salt);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException exception){
            System.out.println("token recebido:" + token);
            return "";
        }
    }
}
