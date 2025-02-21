package payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        String titolo,
        @NotEmpty (message = "La descrizione è un campo obbligatorio!")
        String descrizione,
        @NotNull(message = "La data è un campo obbligatorio!")
        LocalDate data,
        @NotEmpty (message = "Il luogo è un campo obbligatorio!")
        String luogo,
        @NotNull (message = "Il numero dei posti disponibili è un campo obbligatorio!")
        int numero_posti
) {
}