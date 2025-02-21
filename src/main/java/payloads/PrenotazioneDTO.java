package payloads;

import jakarta.validation.constraints.NotEmpty;

public record PrenotazioneDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        String evento,
        @NotEmpty (message = "La descrizione è un campo obbligatorio!")
        String utente
) {
}
