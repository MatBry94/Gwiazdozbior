import java.util.logging.Level;
import java.util.List;
import java.util.Arrays;
import java.io.Serializable;

public class Gwiazda implements Serializable {
    private String nazwa;
    private String nazwaKatalogowa;
    private WspolrzednaAstronomiczna deklinacja;
    private WspolrzednaAstronomiczna rektascensja;
    private double obserwowanaWielkoscGwiazdowa;
    private double absolutnaWielkoscGwiazdowa;
    private double odleglosc;
    private String gwiazdozbior;
    public enum Polkula {
        PN,
        PD
    }
    private Polkula polkula;
    private double temperatura;
    private double masa;

    public Gwiazda(String nazwa, String nazwaKatalogowa, WspolrzednaAstronomiczna deklinacja,
                   WspolrzednaAstronomiczna rektascensja, double obserwowanaWielkoscGwiazdowa,
                   double odleglosc, String gwiazdozbior, Polkula polkula,
                   double temperatura, double masa) {
        if (!validateNazwa(nazwa)) {
            throw new IllegalArgumentException("Nieprawidłowy format nazwy gwiazdy.");
        }
        /*if (!validateNazwaKatalogowa(nazwaKatalogowa)) {
            throw new IllegalArgumentException("Nieprawidłowy format nazwy katalogowej.");
        }*/
        this.nazwa = nazwa;
        this.nazwaKatalogowa = nazwaKatalogowa;
        this.deklinacja = deklinacja;
        this.rektascensja = rektascensja;
        this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        this.odleglosc = odleglosc;
        this.gwiazdozbior = walidujGwiazdozbior(gwiazdozbior); // Użycie metody walidacji dla gwiazdozbioru
        this.polkula = polkula;
        ustawTemperature(temperatura);
        ustawMase(masa);
    }

    // Getters and Setters

    private boolean validateNazwa(String nazwa) {
        return nazwa.matches("^[A-Z]{3}\\d{4}$");
    }

    /*private boolean validateNazwaKatalogowa(String nazwaKatalogowa) {
        return nazwaKatalogowa.matches("^(Alfa|Beta|Gamma|Delta|Epsilon|Zeta|Eta|Theta|Iota|Kappa|Lambda|Mu|Nu|Xi|Omicron|Pi|Rho|Sigma|Tau|Upsilon|Phi|Chi|Psi|Omega) [A-Z][a-z]+$");
    }*/

    private String walidujGwiazdozbior(String gwiazdozbior) throws IllegalArgumentException {
        List<String> dozwoloneGwiazdozbiory = Arrays.asList(
                "Orion", "Pies Wielki", "Wieloryb", "Herkules", "Woźnica",
                "Wolarz", "Hydra", "Panna", "Andromeda", "Smok"
        );

        if (dozwoloneGwiazdozbiory.contains(gwiazdozbior)) {
            return gwiazdozbior;
        } else {
            throw new IllegalArgumentException("Podany gwiazdozbiór '" + gwiazdozbior + "' nie jest jednym z 10 największych.");
        }
    }

    public void setObserwowanaWielkoscGwiazdowa(double wielkosc) {
        if (wielkosc < -26.74 || wielkosc > 15.00) {
            throw new IllegalArgumentException("Nieprawidłowa obserwowana wielkość gwiazdowa.");
        }
        this.obserwowanaWielkoscGwiazdowa = wielkosc;
    }

    public double getObserwowanaWielkoscGwiazdowa() {
        return this.obserwowanaWielkoscGwiazdowa;
    }
    public double obliczAbsolutnaWielkoscGwiazdowa() {
        if (odleglosc <= 0) {
            throw new IllegalArgumentException("Odległość musi być większa od zera.");
        }

        // Konwersja odległości z lat świetlnych na parseki
        double odlegloscWParskach = odleglosc / 3.26;

        // Obliczanie absolutnej wielkości gwiazdowej
        return obserwowanaWielkoscGwiazdowa - 5 * Math.log10(odlegloscWParskach) + 5;
    }

    public String getGwiazdozbior() {
        return this.gwiazdozbior;
    }

    public Polkula getPolkula() {
        return this.polkula;
    }

    public void setPolkula(Polkula polkula) {
        this.polkula = polkula;
    }
    public void ustawTemperature(double temperatura) {
        if (temperatura < 2000) {
            throw new IllegalArgumentException("Temperatura gwiazdy musi być równa lub wyższa niż 2000 stopni Celsjusza.");
        }
        this.temperatura = temperatura;
    }
    public void ustawMase(double masa) {
        if (masa < 0.1 || masa > 50) {
            throw new IllegalArgumentException("Masa gwiazdy musi być w zakresie od 0.1 do 50 mas Słońca.");
        }
        this.masa = masa;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gwiazda:\n");
        sb.append("Nazwa: '").append(nazwa).append("'\n");
        sb.append("Nazwa Katalogowa: '").append(nazwaKatalogowa).append("'\n");
        sb.append("Deklinacja: ").append(deklinacja).append("\n");
        sb.append("Rektascensja: ").append(rektascensja).append("\n");
        sb.append("Obserwowana Wielkość Gwiazdowa: ").append(String.format("%.2f", obserwowanaWielkoscGwiazdowa)).append("\n");
        sb.append("Absolutna Wielkość Gwiazdowa: ").append(String.format("%.2f", absolutnaWielkoscGwiazdowa)).append("\n");
        sb.append("Odległość: ").append(String.format("%.2f", odleglosc)).append(" lat świetlnych\n");
        sb.append("Gwiazdozbiór: '").append(gwiazdozbior).append("'\n");
        sb.append("Półkula: '").append(polkula).append("'\n");
        sb.append("Temperatura: ").append(String.format("%.2f", temperatura)).append(" K\n");
        sb.append("Masa: ").append(masa).append(" mas Słońca\n");
        return sb.toString();
    }
    }