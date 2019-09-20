import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public abstract class Stuk {
    protected final boolean KLEUR;


    public Stuk(boolean KLEUR) {
        this.KLEUR = KLEUR;
    }

    public abstract ArrayList<Zet> getMogelijkeZetten(Speelveld veld, int rij, int kolom);

    public boolean getKLEUR() {
        return KLEUR;
    }

    public abstract String toString();

    public boolean magPromoveren() {
        return false;
    }

}
