package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Prenotazione(Evento evento, Utente utente) {
        this.evento = evento;
        this.utente = utente;
    }
}