/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Pion extends Stuk {
    public Pion(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public void updateMogelijkeZetten(Speelveld veld) {
        //clear hashmap
        mogelijkeZetten.clear();

        //als het een witte pion is (nog rekening houden met array out of bounds)
        if (getKleur() == true) {
            if (rij - 1 >= 0) {
                if (veld.getStukken()[rij - 1][kolom] == null) {
                    mogelijkeZetten.add(new Zet(rij - 1, kolom));
                }
                if (kolom - 1 >= 0) {
                    if (veld.getStukken()[rij - 1][kolom - 1] != null && veld.getStukken()[rij - 1][kolom - 1].getKleur() != kleur) {
                        mogelijkeZetten.add(new Zet(rij - 1, kolom - 1));
                    }
                }
                if (kolom + 1 <= 7) {
                    if (veld.getStukken()[rij - 1][kolom + 1] != null && veld.getStukken()[rij - 1][kolom + 1].getKleur() != kleur) {
                        mogelijkeZetten.add(new Zet(rij - 1, kolom + 1));
                    }
                }
            }
        }
        //als het een zwarte pion is (nog rekening houden met array out of bounds)
        else {
            if (rij + 1 <= 7) {
                if (veld.getStukken()[rij + 1][kolom] == null) {
                    mogelijkeZetten.add(new Zet(rij + 1, kolom));
                }
                if (kolom - 1 >= 0) {
                    if (veld.getStukken()[rij + 1][kolom - 1] != null && veld.getStukken()[rij + 1][kolom - 1].getKleur() != kleur) {
                        mogelijkeZetten.add(new Zet(rij + 1, kolom - 1));
                    }
                }
                if (kolom + 1 <= 7) {
                    if (veld.getStukken()[rij + 1][kolom + 1] != null && veld.getStukken()[rij + 1][kolom + 1].getKleur() != kleur) {
                        mogelijkeZetten.add(new Zet(rij + 1, kolom + 1));
                    }
                }
            }
        }
    }

    public String toString() {
        if (kleur) {
            return "X";
        } else return "x";
    }


}
