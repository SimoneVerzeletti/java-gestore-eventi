import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    private String titolo;
    private LocalDate data;
    private int nPosti;
    private int nPrenotazioni;

    // Costruttore
    public Evento(String titolo, LocalDate data, int nPosti) {
        if (nPosti <= 0) {
            throw new IllegalArgumentException("Il numero di posti deve essere positivo.");
        }
        controlloDataErrata(data);

        this.titolo = titolo;
        this.data = data;
        this.nPosti = nPosti;
        this.nPrenotazioni = 0;
    }
    
// Getter e Setter
        public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        controlloDataErrata(data);
        this.data = data;
    }

    public int getnPosti() {
        return nPosti;
    }

    public int getnPrenotazioni() {
        return nPrenotazioni;
    }

    // Metodi
    private void controlloDataErrata(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Inserisci una data che non sia passata.");
        }
    }

    private void controlloDataPassata() {
        if (this.data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato.");
        }
    }

    private void controlloPrenotazioni() {
        if (nPrenotazioni >= nPosti) {
            throw new IllegalStateException("Non ci sono più posti disponibili.");
        }
    }

    public void prenota() {
        controlloDataPassata();
        controlloPrenotazioni();
        nPrenotazioni++;
    }

    public void disdici() {
        controlloDataPassata();
        if (nPrenotazioni <= 0) {
            throw new IllegalStateException("Non ci sono prenotazioni da disdire.");
        }
        nPrenotazioni--;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo;
    }
}
