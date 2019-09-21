import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Paard extends Stuk {
    public Paard(boolean kleur) {
        super(kleur);
    }

    @Override
    public ArrayList<Zet> getMogelijkeZetten(Speelveld veld, int rij, int kolom) {
        ArrayList<Zet> stukArrayList = new ArrayList<>();

        //linksboven 1

        if (rij - 1 >= 0 && kolom - 2 >= 0) {
            if (veld.getSTUKKEN()[rij - 1][kolom - 2] == null || veld.getSTUKKEN()[rij - 1][kolom - 2].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 1, kolom - 2));
            }
        }

        //linksboven 2
        if (rij - 2 >= 0 && kolom - 1 >= 0) {
            if (veld.getSTUKKEN()[rij - 2][kolom - 1] == null || veld.getSTUKKEN()[rij - 2][kolom - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 2, kolom - 1));
            }
        }

        //rechtsboven 1
        if (rij - 2 >= 0 && kolom + 1 <= 7) {
            if (veld.getSTUKKEN()[rij - 2][kolom + 1] == null || veld.getSTUKKEN()[rij - 2][kolom + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 2, kolom + 1));
            }
        }

        //rechtsboven 2
        if (rij - 1 >= 0 && kolom + 2 <= 7) {
            if (veld.getSTUKKEN()[rij - 1][kolom + 2] == null || veld.getSTUKKEN()[rij - 1][kolom + 2].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij - 1, kolom + 2));
            }
        }

        //rechtsonder 1
        if (rij + 1 <= 7 && kolom + 2 <= 7) {
            if (veld.getSTUKKEN()[rij + 1][kolom + 2] == null || veld.getSTUKKEN()[rij + 1][kolom + 2].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 1, kolom + 2));
            }
        }

        //rechtsonder 2
        if (rij + 2 <= 7 && kolom + 1 <= 7) {
            if (veld.getSTUKKEN()[rij + 2][kolom + 1] == null || veld.getSTUKKEN()[rij + 2][kolom + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 2, kolom + 1));
            }
        }

        //linksonder 1
        if (rij + 2 <= 7 && kolom - 1 >= 0) {
            if (veld.getSTUKKEN()[rij + 2][kolom - 1] == null || veld.getSTUKKEN()[rij + 2][kolom - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 2, kolom - 1));
            }
        }

        //linksonder 2
        if (rij + 1 <= 7 && kolom - 2 >= 0) {
            if (veld.getSTUKKEN()[rij + 1][kolom - 2] == null || veld.getSTUKKEN()[rij + 1][kolom - 2].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij + 1, kolom - 2));
            }
        }

        return stukArrayList;
    }

    @Override
    public String toString() {
        if (KLEUR) {
            return "N";
        } else return "n";
    }
}
