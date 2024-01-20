import java.io.Serializable;

public class WspolrzednaAstronomiczna implements Serializable {private final int stopnie;
    private final int minuty;
    private final double sekundy;

    public WspolrzednaAstronomiczna(int stopnie, int minuty, double sekundy) {
        if (!isValid(stopnie, minuty, sekundy)) {
            throw new IllegalArgumentException("Nieprawidłowa wartość współrzędnej astronomicznej.");
        }
        this.stopnie = stopnie;
        this.minuty = minuty;
        this.sekundy = sekundy;
    }

    private boolean isValid(int stopnie, int minuty, double sekundy) {
        return (stopnie >= -90 && stopnie <= 90) && (minuty >= 0 && minuty < 60) && (sekundy >= 0 && sekundy < 60);
    }

    // Getters

    public int getStopnie() {
        return stopnie;
    }

    public int getMinuty() {
        return minuty;
    }

    public double getSekundy() {
        return sekundy;
    }

    // Metoda do ładnego wyświetlania współrzędnej
    @Override
    public String toString() {
        return String.format("%d° %d' %.2f\"", stopnie, minuty, sekundy);
    }
}
