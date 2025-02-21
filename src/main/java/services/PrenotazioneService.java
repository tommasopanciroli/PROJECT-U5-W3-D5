package services;

import entities.Evento;
import entities.Prenotazione;
import entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payloads.PrenotazioneDTO;
import repos.PrenotazioneRepository;

import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    UtenteService utenteService;
    @Autowired
    EventoService eventoService;
    public Prenotazione save(PrenotazioneDTO prenotazioneDTO){
        Evento evento = eventoService.findById(UUID.fromString(prenotazioneDTO.evento()));
        Utente utente = utenteService.findById(UUID.fromString(prenotazioneDTO.utente()));
        Prenotazione prenotazione = new Prenotazione(evento, utente);
        return prenotazioneRepository.save(prenotazione);
    }
}