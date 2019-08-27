import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Toren extends Stuk {
    public Toren(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public ArrayList<Zet> updateMogelijkeZetten(Speelveld veld) {

        return null;
    }

    @Override
    public String toString() {
        if (kleur) {
            return "R";
        } else return "r";
    }
}
