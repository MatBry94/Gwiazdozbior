import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            BazaGwiazd bazaGwiazd = new BazaGwiazd();
            bazaGwiazd.wyswietlWszystkieGwiazdy();

            // Nazwa i nazwa katalogowa

            System.out.print("Wprowadź nazwę gwiazdy(3 duże litery i 4 cyfry): ");
            String nazwa = scanner.nextLine();

            // Gwiazdozbiór
            System.out.print("Wprowadź gwiazdozbiór (Dostępne: Orion,Pies Wielki, Wieloryb, Herkules, Woźnica, Wolarz, Hydra, Panna, Andromeda, Smok): ");
            String gwiazdozbior = scanner.next();



            // Deklinacja
            System.out.println("Wprowadź deklinację:");
            System.out.print("Stopnie od - 90 do 90: ");
            int stopnieDeklinacji = scanner.nextInt();
            System.out.print("Minuty od 0 do 60: ");
            int minutyDeklinacji = scanner.nextInt();
            System.out.print("Sekundy od 0 do 60: ");
            double sekundyDeklinacji = scanner.nextDouble();
            WspolrzednaAstronomiczna deklinacja = new WspolrzednaAstronomiczna(stopnieDeklinacji, minutyDeklinacji, sekundyDeklinacji);

            // Rektascensja
            System.out.println("Wprowadź rektascensję:");
            System.out.print("Godziny od 00 do 24: ");
            int godzinyRektascensji = scanner.nextInt();
            System.out.print("Minuty od 0 do 60: ");
            int minutyRektascensji = scanner.nextInt();
            System.out.print("Sekundy od 0 do 60: ");
            double sekundyRektascensji = scanner.nextDouble();
            WspolrzednaAstronomiczna rektascensja = new WspolrzednaAstronomiczna(godzinyRektascensji, minutyRektascensji, sekundyRektascensji);

            // Obserwowana wielkość gwiazdowa
            System.out.print("Wprowadź obserwowaną wielkość gwiazdową od -26.74 do 15.00: ");
            double obserwowanaWielkoscGwiazdowa = scanner.nextDouble();

            // Odległość
            System.out.print("Wprowadź odległość w latach świetlnych większa od zera : ");
            double odleglosc = scanner.nextDouble();



            // Półkula
            System.out.print("Wprowadź półkulę (PN/PD): ");
            String polkulaStr = scanner.next();
            Gwiazda.Polkula polkula = polkulaStr.equalsIgnoreCase("PN") ? Gwiazda.Polkula.PN : Gwiazda.Polkula.PD;

            // Temperatura
            System.out.print("Wprowadź temperaturę (Temperatura gwiazdy musi być równa lub wyższa niż 2000 stopni Celsjusza): ");
            double temperatura = scanner.nextDouble();

            // Masa
            System.out.print("Wprowadź masę (w masach Słońca) od 0.1 do 50: ");
            double masa = scanner.nextDouble();





        bazaGwiazd.dodajGwiazde(nazwa, deklinacja, rektascensja, obserwowanaWielkoscGwiazdowa,
                    odleglosc, gwiazdozbior, polkula, temperatura, masa);
        bazaGwiazd.zapiszGwiazdyDoPliku();

            Gwiazda ostatnioDodanaGwiazda = bazaGwiazd.getOstatnioDodanaGwiazda();
            if (ostatnioDodanaGwiazda != null) {
                    System.out.println(ostatnioDodanaGwiazda);
            } else {
                    System.out.println("Nie dodano jeszcze żadnej gwiazdy.");
            }



            // Wyświetlenie informacji o utworzonej gwiazdzie
        System.out.println(ostatnioDodanaGwiazda);

        scanner.close();
    }
}
