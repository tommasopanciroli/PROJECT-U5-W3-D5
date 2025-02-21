package payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio")
        @Size(min = 3, max = 40, message = "Il nome deve essere compreso tra i 3 ed i 40 caratteri")
        String name,
        @NotEmpty(message = "Il cognome è un campo obbligatorio")
        @Size(min = 3, max = 40, message = "Il cognome deve essere compreso tra i 3 ed i 40 caratteri")
        String surname,
        @Email(message = "Indirizzo non valido.")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio")
        @Size(min = 3, max = 40, message = "La password deve essere compresa tra i 3 ed i 40 caratteri")
        String password,
        @NotEmpty(message = "Il ruolo è un campo obbligatorio")
        String ruoloUtente
) {
}
