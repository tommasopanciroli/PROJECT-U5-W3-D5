package payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UtenteLoginDTO (
        @Email(message = "Indirizzo non valido!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password
) {
}