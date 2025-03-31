import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UsaEvento {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Evento evento = null;

        // Creazione dell'evento con controlli
        boolean eventoCreato = false;
        while (!eventoCreato) {
            try {
                System.out.print("Inserisci il titolo dell'evento: ");
                String titolo = scanner.nextLine();

                System.out.print("Inserisci la data dell'evento (dd/MM/yyyy): ");
                String dataString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataString, formatter);

                System.out.print("Inserisci il numero totale di posti: ");
                int nPosti = scanner.nextInt();
                scanner.nextLine(); // Consuma il carattere di invio

                evento = new Evento(titolo, data, nPosti);
                eventoCreato = true;

            } catch (IllegalArgumentException errore) {
                System.out.println("Errore nella creazione dell'evento: " + errore.getMessage());
                System.out.println("Inserisci di nuovo i dati.\n");
            }
        }

        // Prenotazioni
        int nPrenotazioni = 0;
        boolean numeroValido = false;
        
        while (!numeroValido) {
            System.out.print("Quante prenotazioni vuoi fare? (Posti disponibili: " 
            + (evento.getnPosti() - evento.getnPrenotazioni()) 
            + "): ");
            nPrenotazioni = scanner.nextInt();
            scanner.nextLine();
        
            if (nPrenotazioni <= (evento.getnPosti() - evento.getnPrenotazioni()) && nPrenotazioni >= 0) {
                numeroValido = true;
            } else {
                System.out.println("Numero non valido! Puoi prenotare massimo " 
                + (evento.getnPosti() - evento.getnPrenotazioni()) 
                + " posti.");
            }
        }
        
        for (int i = 0; i < nPrenotazioni; i++) {
            try {
                evento.prenota();
            } catch (IllegalStateException errore) {
                System.out.println("Errore prenotazione: " + errore.getMessage());
                break;
            }
        }     

        // Stampa stato prenotazioni
        System.out.println("Posti prenotati: " + evento.getnPrenotazioni());
        System.out.println("Posti disponibili: " + (evento.getnPosti() - evento.getnPrenotazioni()));

        // Disdette
        int nDisdette = 0;
        boolean numeroDisdetteValido = false;
        
        while (!numeroDisdetteValido) {
            System.out.print("Quante disdette vuoi fare? (Prenotazioni attuali: " + evento.getnPrenotazioni() + "): ");
            nDisdette = scanner.nextInt();
            scanner.nextLine(); // consuma invio
        
            if (nDisdette <= evento.getnPrenotazioni() && nDisdette >= 0) {
                numeroDisdetteValido = true;
            } else {
                System.out.println("Numero non valido! Puoi disdire massimo " + evento.getnPrenotazioni() + " posti.");
            }
        }
        
        for (int i = 0; i < nDisdette; i++) {
            try {
                evento.disdici();
            } catch (IllegalStateException errore) {
                System.out.println("Errore disdetta: " + errore.getMessage());
                break;
            }
        }

        // Stampa stato finale
        System.out.println("Posti prenotati: " + evento.getnPrenotazioni());
        System.out.println("Posti disponibili: " + (evento.getnPosti() - evento.getnPrenotazioni()));


    }
}
