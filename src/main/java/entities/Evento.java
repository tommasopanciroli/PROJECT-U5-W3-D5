package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numero_posti;
    @ManyToOne
    @JoinColumn(name = "id_organizzatore", nullable = false)
    private Utente utente;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int numero_posti, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numero_posti = numero_posti;
        this.utente = utente;
    }
}
