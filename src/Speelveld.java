import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public final class Speelveld {
    private final Stuk[][] STUKKEN;


    //spel wordt voor het eerst opgestart
    public Speelveld() {
        STUKKEN = new Stuk[8][8];

        creeerStukken();
        updateAlleMogelijkeZetten();
    }

    public Speelveld(Stuk[][] STUKKEN) {
        this.STUKKEN = STUKKEN;
        updateAlleMogelijkeZetten();
    }

    public boolean checkGameOver() {
        boolean koningWit = false;
        boolean koningZwart = false;
        for (int i = 0; i < getSTUKKEN().length; i++) {
            for (int j = 0; j < getSTUKKEN()[i].length; j++) {
                if (getSTUKKEN()[i][j] instanceof Koning) {
                    if (getSTUKKEN()[i][j].KLEUR) {
                        koningWit = true;
                    }
                    if (!getSTUKKEN()[i][j].KLEUR) {
                        koningZwart = true;
                    }
                }
            }
        }
        if (koningWit && !koningZwart) {
            printVeld();
            System.out.println("Wit heeft gewonnen");
            return true;
        }
        if (!koningWit && koningZwart) {
            printVeld();
            System.out.println("Zwart heeft gewonnen");
            return true;
        }
        return false;
    }

    public void creeerStukken() {
        //alle zwarte stukken
        STUKKEN[0][0] = new Toren(false, 0, 0);
        STUKKEN[0][1] = new Paard(false, 0, 1);
        STUKKEN[0][2] = new Loper(false, 0, 2);
        STUKKEN[0][3] = new Koningin(false, 0, 3);
        STUKKEN[0][4] = new Koning(false, 0, 4);
        STUKKEN[0][5] = new Loper(false, 0, 5);
        STUKKEN[0][6] = new Paard(false, 0, 6);
        STUKKEN[0][7] = new Toren(false, 0, 7);
        //pionen
        for (int i = 0; i < 8; i++) {
            STUKKEN[1][i] = new Pion(false, 1, i);
            STUKKEN[6][i] = new Pion(true, 6, i);
        }
        //alle witte stukken
        STUKKEN[7][0] = new Toren(true, 7, 0);
        STUKKEN[7][1] = new Paard(true, 7, 1);
        STUKKEN[7][2] = new Loper(true, 7, 2);
        STUKKEN[7][3] = new Koningin(true, 7, 3);
        STUKKEN[7][4] = new Koning(true, 7, 4);
        STUKKEN[7][5] = new Loper(true, 7, 5);
        STUKKEN[7][6] = new Paard(true, 7, 6);
        STUKKEN[7][7] = new Toren(true, 7, 7);
    }

    public void printVeld() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Stuk ditStuk = getSTUKKEN()[i][j];
                if (ditStuk == null) {
                    System.out.print(' ');
                } else System.out.print(getSTUKKEN()[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public ArrayList<Zet> updateAlleMogelijkeZetten() {
        final ArrayList<Zet> ALLEMOGELIJKEZETTEN = new ArrayList<>();
        for (int i = 0; i < STUKKEN.length; i++) {
            for (int j = 0; j < STUKKEN[i].length; j++) {
                if (STUKKEN[i][j] != null) {
                    ArrayList<Zet> stukArrayList = STUKKEN[i][j].updateMogelijkeZetten(this);

                    if (stukArrayList != null) {
                        ALLEMOGELIJKEZETTEN.addAll(stukArrayList);
                    }
                }
            }
        }
        return ALLEMOGELIJKEZETTEN;
    }

    public Speelveld move(Zet zet) {
        Stuk[][] stukkenCopy = copyArray();

        //het stuk op de startPlek van 'stukken' array wordt verplaatst naar de eindplek  bij de 'stukkenCopy' array
        stukkenCopy[zet.getEINDRIJ()][zet.getEINDKOLOM()] = STUKKEN[zet.getSTARTRIJ()][zet.getSTARTKOLOM()];

        //verander de interne coordinaten van het verplaatste stuk
        stukkenCopy[zet.getEINDRIJ()][zet.getEINDKOLOM()].setCoordinaten(zet.getEINDRIJ(), zet.getEINDKOLOM());

        //maak de plek leeg in de copy array
        stukkenCopy[zet.getSTARTRIJ()][zet.getSTARTKOLOM()] = null;

        return new Speelveld(controleerPromotie(stukkenCopy));
    }

    private Stuk[][] copyArray() {
        Stuk[][] stukkenCopy = new Stuk[8][8];
        for (int i = 0; i < stukkenCopy.length; i++) {
            for (int j = 0; j < stukkenCopy[i].length; j++) {
                stukkenCopy[i][j] = STUKKEN[i][j];
            }
        }
        return stukkenCopy;
    }

    private Stuk[][] controleerPromotie(Stuk[][] stukken) {

        //witte promotie
        for (int i = 0; i <= 7; i++) {
            if (stukken[0][i] != null && stukken[0][i].magPromoveren()) {
                stukken[0][i] = new Koningin(true, 0, i);
            }
        }
        //zwarte promotie
        for (int i = 0; i <= 7; i++) {
            if (stukken[7][i] != null && stukken[7][i].magPromoveren()) {
                stukken[7][i] = new Koningin(true, 7, i);
            }
        }
        return stukken;
    }

    public boolean checkSpelerMagGekozenStukBewegen(int rij, int kolom, boolean beurt) {
        if (rij > 7 || rij < 0 || kolom > 7 || kolom < 0) {
            System.out.println("Deze coordinaten vallen buiten het bord");
        } else {
            if (STUKKEN[rij][kolom] == null) {
                System.out.println("Er bevind zich geen stuk op de ingevoerde coordinaten");
                return false;
            }
            if (STUKKEN[rij][kolom].KLEUR != beurt) {
                System.out.println("De speler mag dit stuk niet bewegen (andere kleur)");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean stukMagNaarGekozenVeld(Zet zet, ArrayList<Zet> ALLEMOGELIJKEZETTEN) {
        for (int i = 0; i < ALLEMOGELIJKEZETTEN.size(); i++) {

            if (ALLEMOGELIJKEZETTEN.get(i).getSTARTRIJ() == zet.getSTARTRIJ() &&
                    ALLEMOGELIJKEZETTEN.get(i).getSTARTKOLOM() == zet.getSTARTKOLOM() &&
                    ALLEMOGELIJKEZETTEN.get(i).getEINDRIJ() == zet.getEINDRIJ() &&
                    ALLEMOGELIJKEZETTEN.get(i).getEINDKOLOM() == zet.getEINDKOLOM()) {
                return true;
            }
        }
        System.out.println("Dit is geen geldige zet!");
        return false;
    }

    public Stuk[][] getSTUKKEN() {
        return STUKKEN;
    }
}
