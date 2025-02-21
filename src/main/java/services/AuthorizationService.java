package services;

import entities.Utente;
import exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import payloads.UtenteLoginDTO;
import security.JWTTools;

@Service
public class AuthorizationService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UtenteLoginDTO payload){
        Utente utente = this.utenteService.findByEmail(payload.email());
        if(bcrypt.matches(payload.password(), utente.getPassword())){
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}