import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Koningin extends Stuk {
    public Koningin(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public ArrayList<Zet> updateMogelijkeZetten(Speelveld veld) {
        ArrayList<Zet> stukArrayList = new ArrayList<>();

        int rijCoordinaat = rij;
        int kolomCoordinaat = kolom;

        //links
        while (kolomCoordinaat > 0) {
            if (veld.getSTUKKEN()[rij][kolomCoordinaat - 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolomCoordinaat - 1));
                kolomCoordinaat--;
            } else if (veld.getSTUKKEN()[rij][kolomCoordinaat - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolomCoordinaat - 1));
                kolomCoordinaat = 0;
            } else {
                kolomCoordinaat = 0;
            }
        }
        //boven
        while (rijCoordinaat > 0) {
            if (veld.getSTUKKEN()[rijCoordinaat - 1][kolom] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolom));
                rijCoordinaat--;
            } else if (veld.getSTUKKEN()[rijCoordinaat - 1][kolom].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolom));
                rijCoordinaat = 0;
            } else {
                rijCoordinaat = 0;
            }
        }
        //RESET RIJ COORDINAAT
        rijCoordinaat = rij;
        kolomCoordinaat = kolom;

        //rechts
        while (kolomCoordinaat < 7) {
            if (veld.getSTUKKEN()[rij][kolomCoordinaat + 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolomCoordinaat + 1));
                kolomCoordinaat++;
            } else if (veld.getSTUKKEN()[rij][kolomCoordinaat + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rij, kolomCoordinaat + 1));
                kolomCoordinaat = 7;
            } else {
                kolomCoordinaat = 7;
            }
        }

        //beneden

        while (rijCoordinaat < 7) {
            if (veld.getSTUKKEN()[rijCoordinaat + 1][kolom] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolom));
                rijCoordinaat++;
            } else if (veld.getSTUKKEN()[rijCoordinaat + 1][kolom].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolom));
                rijCoordinaat = 7;
            } else {
                rijCoordinaat = 7;
            }
        }

        rijCoordinaat = rij;
        kolomCoordinaat = kolom;

        //schuinlinksboven
        while (rijCoordinaat > 0 && kolomCoordinaat > 0) {
            if (veld.getSTUKKEN()[rijCoordinaat - 1][kolomCoordinaat - 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolomCoordinaat - 1));
                rijCoordinaat--;
                kolomCoordinaat--;
            } else if (veld.getSTUKKEN()[rijCoordinaat - 1][kolomCoordinaat - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolomCoordinaat - 1));
                rijCoordinaat = 0;
                kolomCoordinaat = 0;
            } else {
                rijCoordinaat = 0;
                kolomCoordinaat = 0;
            }
        }
        rijCoordinaat = rij;
        kolomCoordinaat = kolom;

        //schuinrechtsboven
        while (rijCoordinaat > 0 && kolomCoordinaat < 7) {
            if (veld.getSTUKKEN()[rijCoordinaat - 1][kolomCoordinaat + 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolomCoordinaat + 1));
                rijCoordinaat--;
                kolomCoordinaat++;
            } else if (veld.getSTUKKEN()[rijCoordinaat - 1][kolomCoordinaat + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat - 1, kolomCoordinaat + 1));
                rijCoordinaat = 0;
                kolomCoordinaat = 7;
            } else {
                rijCoordinaat = 0;
                kolomCoordinaat = 7;
            }
        }
        rijCoordinaat = rij;
        kolomCoordinaat = kolom;

        //schuinrechtsonder
        while (rijCoordinaat < 7 && kolomCoordinaat < 7) {
            if (veld.getSTUKKEN()[rijCoordinaat + 1][kolomCoordinaat + 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolomCoordinaat + 1));
                rijCoordinaat++;
                kolomCoordinaat++;
            } else if (veld.getSTUKKEN()[rijCoordinaat + 1][kolomCoordinaat + 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolomCoordinaat + 1));
                rijCoordinaat = 7;
                kolomCoordinaat = 7;
            } else {
                rijCoordinaat = 7;
                kolomCoordinaat = 7;
            }
        }
        rijCoordinaat = rij;
        kolomCoordinaat = kolom;

        //schuinlinksonder
        while (rijCoordinaat < 7 && kolomCoordinaat > 0) {
            if (veld.getSTUKKEN()[rijCoordinaat + 1][kolomCoordinaat - 1] == null) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolomCoordinaat - 1));
                rijCoordinaat++;
                kolomCoordinaat--;
            } else if (veld.getSTUKKEN()[rijCoordinaat + 1][kolomCoordinaat - 1].KLEUR != KLEUR) {
                stukArrayList.add(new Zet(rij, kolom, rijCoordinaat + 1, kolomCoordinaat - 1));
                rijCoordinaat = 7;
                kolomCoordinaat = 0;
            } else {
                rijCoordinaat = 7;
                kolomCoordinaat = 0;
            }
        }


        return stukArrayList;
    }

    @Override
    public String toString() {
        if (KLEUR) {
            return "Q";
        } else return "q";
    }
}
