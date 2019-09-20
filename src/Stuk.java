import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public abstract class Stuk {
    protected final boolean KLEUR;
    protected int rij;
    protected int kolom;


    public Stuk(boolean KLEUR, int rij, int kolom) {
        this.KLEUR = KLEUR;
        this.rij = rij;
        this.kolom = kolom;
    }

    public abstract ArrayList<Zet> getMogelijkeZetten(Speelveld veld);

    public void setCoordinaten(int rij, int kolom) {
        this.rij = rij;
        this.kolom = kolom;
    }

    public boolean getKLEUR() {
        return KLEUR;
    }

    public abstract String toString();

    public boolean magPromoveren() {
        return false;
    }

}
