import java.io.Serializable;
public class Rektascensja {private final int godziny;
    private final int minuty;
    private final double sekundy;

    public Rektascensja(int godziny, int minuty, double sekundy) {
        if (!isValid(godziny, minuty, sekundy)) {
            throw new IllegalArgumentException("Nieprawidłowa wartość rektascensji.");
        }
        this.godziny = godziny;
        this.minuty = minuty;
        this.sekundy = sekundy;
    }

    private boolean isValid(int godziny, int minuty, double sekundy) {
        return (godziny >= 0 && godziny <= 24) && (minuty >= 0 && minuty < 60) && (sekundy >= 0 && sekundy < 60);
    }

    // Gettery, toString()...

    public String toString() {
        return String.format("%dh %dm %.2fs", godziny, minuty, sekundy);
    }
}
