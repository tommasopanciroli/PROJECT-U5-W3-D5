package services;

import entities.Utente;
import enums.RuoloUtente;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import payloads.UtenteDTO;
import repos.UtenteRepository;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente non trovato!"));
    }
    public RuoloUtente ruoloUtente (UtenteDTO utenteDTO){
        try {
            return RuoloUtente.valueOf(utenteDTO.ruoloUtente().toUpperCase());
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("Il ruolo dell'utente deve essere ORGANIZZATORE_EVENTI o UTENTE_NORMALE!");
        }
    }
    public Utente save(UtenteDTO utenteDTO) {
        utenteRepository.findByEmail(utenteDTO.email()).ifPresent(
                utente -> {
                    throw new BadRequestException("L'email " + utenteDTO.email() + " è già in uso!");
                }
        );
        Utente utente = new Utente(utenteDTO.name(), utenteDTO.surname(), utenteDTO.email(), bcrypt.encode(utenteDTO.password()), ruoloUtente(utenteDTO));
        return this.utenteRepository.save(utente);
    }
}