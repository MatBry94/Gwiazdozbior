import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;


public class BazaGwiazd {
    private List<Gwiazda> gwiazdy;
    private Map<String, Integer> licznikGwiazdWGwiazdozbiorze;

    public BazaGwiazd() {
        this.gwiazdy = new ArrayList<>();
        this.licznikGwiazdWGwiazdozbiorze = new HashMap<>();
        wczytajGwiazdyZPliku();

    }
    public Gwiazda getOstatnioDodanaGwiazda() {
        if (!gwiazdy.isEmpty()) {
            return gwiazdy.get(gwiazdy.size() - 1);
        } else {
            return null; // lub rzuć wyjątek, jeśli preferujesz
        }
    }

    public void dodajGwiazde(String nazwa, WspolrzednaAstronomiczna deklinacja,
                             WspolrzednaAstronomiczna rektascensja, double obserwowanaWielkoscGwiazdowa,
                             double odleglosc, String gwiazdozbior, Gwiazda.Polkula polkula,
                             double temperatura, double masa) {
        // Usunięcie argumentu nazwaKatalogowa, bo jest generowany wewnątrz metody
        String nazwaKatalogowa = wygenerujNazweKatalogowa(gwiazdozbior);
        Gwiazda nowaGwiazda = new Gwiazda(nazwa, nazwaKatalogowa, deklinacja, rektascensja, obserwowanaWielkoscGwiazdowa, odleglosc, gwiazdozbior, polkula, temperatura, masa);
        gwiazdy.add(nowaGwiazda);
    }
    private String wygenerujNazweKatalogowa(String gwiazdozbior) {
        String[] nazwyGrekie = {
                "Alfa", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa",
                "Lambda", "Mu", "Nu", "Xi", "Omicron", "Pi", "Rho", "Sigma", "Tau", "Upsilon",
                "Phi", "Chi", "Psi", "Omega"
        };

        int liczbaGwiazdDoTeraz = licznikGwiazdWGwiazdozbiorze.getOrDefault(gwiazdozbior, 0);
        licznikGwiazdWGwiazdozbiorze.put(gwiazdozbior, liczbaGwiazdDoTeraz + 1);

        if (liczbaGwiazdDoTeraz >= nazwyGrekie.length) {
            throw new IllegalArgumentException("Przekroczono liczbę dostępnych nazw greckich w gwiazdozbiorze " + gwiazdozbior);
        }

        return nazwyGrekie[liczbaGwiazdDoTeraz] + " " + gwiazdozbior;
    }

    public void wyswietlWszystkieGwiazdy() {
        if (gwiazdy.isEmpty()) {
            System.out.println("Baza gwiazd jest pusta.");
        } else {
            System.out.println("Lista gwiazd w bazie:");
            for (Gwiazda gwiazda : gwiazdy) {
                System.out.println(gwiazda); // Tutaj automatycznie zostanie wywołana metoda toString() z klasy Gwiazda
            }
        }
    }
    public void zapiszGwiazdyDoPliku() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gwiazdy.ser"))) {
            out.writeObject(gwiazdy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wczytajGwiazdyZPliku() {
        File file = new File("gwiazdy.ser");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                gwiazdy = (List<Gwiazda>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
