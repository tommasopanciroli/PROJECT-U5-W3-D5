package controllers;

import exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.UtenteDTO;
import payloads.UtenteLoginDTO;
import payloads.UtenteLoginResponseDTO;
import payloads.UtenteResponseDTO;

import services.AuthorizationService;
import services.UtenteService;

@RestController
@RequestMapping("/auth")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponseDTO saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new UtenteResponseDTO(this.utenteService.save(body).getId());
    }
    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody @Validated UtenteLoginDTO payload, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new UtenteLoginResponseDTO(authorizationService.authenticateUserAndGenerateToken(payload));
    }
}