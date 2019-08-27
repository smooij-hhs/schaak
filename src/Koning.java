/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Koning extends Stuk {
    public Koning(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public void updateMogelijkeZetten(Speelveld veld) {

    }

    @Override
    public String toString() {
        if (kleur) {
            return "K";
        } else return "k";
    }
}
