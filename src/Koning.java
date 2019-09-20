import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Koning extends Stuk {
    public Koning(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public ArrayList<Zet> getMogelijkeZetten(Speelveld veld) {
        ArrayList<Zet> stukArrayList = new ArrayList<>();
        //linksboven
        if ((rij - 1 >= 0) && (kolom - 1 >= 0)) {
            if (veld.getSTUKKEN()[rij - 1][kolom - 1] == null || veld.getSTUKKEN()[rij - 1][kolom - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 1, kolom - 1));
            }
        }

        //boven
        if ((rij - 1 >= 0)) {
            if (veld.getSTUKKEN()[rij - 1][kolom] == null || veld.getSTUKKEN()[rij - 1][kolom].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 1, kolom));
            }
        }

        //rechtsboven
        if ((rij - 1 >= 0) && (kolom + 1 <= 7)) {
            if (veld.getSTUKKEN()[rij - 1][kolom + 1] == null || veld.getSTUKKEN()[rij - 1][kolom + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 1, kolom + 1));
            }
        }

        //rechts
        if ((kolom + 1 <= 7)) {
            if (veld.getSTUKKEN()[rij][kolom + 1] == null || veld.getSTUKKEN()[rij][kolom + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolom + 1));
            }
        }

        //rechtssonder
        if ((rij + 1 <= 7) && (kolom + 1 <= 7)) {
            if (veld.getSTUKKEN()[rij + 1][kolom + 1] == null || veld.getSTUKKEN()[rij + 1][kolom + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 1, kolom + 1));
            }
        }

        //onder
        if ((rij + 1 <= 7)) {
            if (veld.getSTUKKEN()[rij + 1][kolom] == null || veld.getSTUKKEN()[rij + 1][kolom].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 1, kolom));
            }
        }

        //linksonder
        if ((rij + 1 <= 7) && (kolom - 1 >= 0)) {
            if (veld.getSTUKKEN()[rij + 1][kolom - 1] == null || veld.getSTUKKEN()[rij + 1][kolom - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 1, kolom - 1));
            }
        }

        //links
        if ((kolom - 1 >= 0)) {
            if (veld.getSTUKKEN()[rij][kolom - 1] == null || veld.getSTUKKEN()[rij][kolom - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolom - 1));
            }
        }


        return stukArrayList;
    }

    @Override
    public String toString() {
        if (KLEUR) {
            return "K";
        } else return "k";
    }
}
