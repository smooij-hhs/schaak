import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Loper extends Stuk {
    public Loper(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public ArrayList<Zet> updateMogelijkeZetten(Speelveld veld) {

        return null;
    }

    @Override
    public String toString() {
        if (kleur) {
            return "B";
        } else return "b";
    }
}
