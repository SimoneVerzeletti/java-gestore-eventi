import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    private LocalTime ora;
    private double prezzo;

// Costruttore
    public Concerto(String titolo, LocalDate data, int nPosti, LocalTime ora, double prezzo) {
        super(titolo, data, nPosti);
        this.ora = ora;
        this.prezzo = prezzo;
    }

// Getter e Setter
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

// Metodi
    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return getData().format(dateFormatter) + " - " + ora.format(timeFormatter);
    }

    public String getPrezzoFormattato() {
        DecimalFormat decimalFormat = new DecimalFormat("##,##€");
        return decimalFormat.format(prezzo);
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
