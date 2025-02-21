package controllers;

import entities.Utente;
import exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payloads.EventoDTO;
import payloads.EventoResponseDTO;
import services.EventoService;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    EventoService eventoService;
    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public EventoResponseDTO creazioneEvento(@RequestBody @Validated EventoDTO eventoDTO, BindingResult bindingResult, @AuthenticationPrincipal Utente utente) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new EventoResponseDTO(eventoService.save(eventoDTO, utente).getId());
    }
}