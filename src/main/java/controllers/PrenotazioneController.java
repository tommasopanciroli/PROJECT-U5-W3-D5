package controllers;

import exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payloads.PrenotazioneDTO;
import payloads.PrenotazioneResponseDTO;
import services.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    PrenotazioneService prenotazioneService;
    @PostMapping
    @PreAuthorize("hasAuthority('UTENTE')")
    public PrenotazioneResponseDTO creazioneEvento(@RequestBody @Validated PrenotazioneDTO prenotazioneDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new PrenotazioneResponseDTO(prenotazioneService.save(prenotazioneDTO).getId());
    }
}