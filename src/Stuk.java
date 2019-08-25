import java.util.HashMap;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public abstract class Stuk {
    protected boolean kleur;
    protected int rij;
    protected int kolom;
    protected HashMap<Integer,Integer> mogelijkeZetten = new HashMap<>();

    public Stuk(boolean kleur, int rij, int kolom){
        this.kleur=kleur;
        this.rij=rij;
        this.kolom=kolom;
    }

    public abstract void updateMogelijkeZetten(Speelveld veld);

    public void setCoordinaten(int rij, int kolom){
        this.rij=rij;
        this.kolom=kolom;
    }

    public boolean getKleur() {
        return kleur;
    }

}
