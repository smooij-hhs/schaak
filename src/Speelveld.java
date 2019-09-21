import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public final class Speelveld {
    public final Stuk[][] STUKKEN;
    public final boolean beurt;


    //spel wordt voor het eerst opgestart
    public Speelveld(boolean beurt) {
        STUKKEN = new Stuk[8][8];

        creeerStukken();

        this.beurt=beurt;
    }

    public Speelveld(Stuk[][] STUKKEN,boolean beurt) {
        this.STUKKEN = STUKKEN;
        getAlleMogelijkeZetten();
        this.beurt=beurt;
    }

    public boolean checkKleurHeeftVerloren(boolean kleur) {
        boolean koningAanwezig = false;

        for (int i = 0; i < getSTUKKEN().length; i++) {
            for (int j = 0; j < getSTUKKEN()[i].length; j++) {
                if (getSTUKKEN()[i][j] instanceof Koning) {
                    if (getSTUKKEN()[i][j].KLEUR==kleur) {
                        koningAanwezig = true;
                    }
                }
            }
        }
        return !koningAanwezig;
        }

    public void creeerStukken() {
        //alle zwarte stukken
        STUKKEN[0][0] = new Toren(false);
        STUKKEN[0][1] = new Paard(false);
        STUKKEN[0][2] = new Loper(false);
        STUKKEN[0][3] = new Koningin(false);
        STUKKEN[0][4] = new Koning(false);
        STUKKEN[0][5] = new Loper(false);
        STUKKEN[0][6] = new Paard(false);
        STUKKEN[0][7] = new Toren(false);
        //pionen
        for (int i = 0; i < 8; i++) {
            STUKKEN[1][i] = new Pion(false);
            STUKKEN[6][i] = new Pion(true);
        }
        //alle witte stukken
        STUKKEN[7][0] = new Toren(true);
        STUKKEN[7][1] = new Paard(true);
        STUKKEN[7][2] = new Loper(true);
        STUKKEN[7][3] = new Koningin(true);
        STUKKEN[7][4] = new Koning(true);
        STUKKEN[7][5] = new Loper(true);
        STUKKEN[7][6] = new Paard(true);
        STUKKEN[7][7] = new Toren(true);
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

    public ArrayList<Zet> getAlleMogelijkeZetten(boolean kleur) {
        final ArrayList<Zet> ALLEMOGELIJKEZETTEN = new ArrayList<>();
        for (int i = 0; i < STUKKEN.length; i++) {
            for (int j = 0; j < STUKKEN[i].length; j++) {
                Stuk stuk = STUKKEN[i][j];
                if (stuk != null && stuk.KLEUR == kleur) {
                    ArrayList<Zet> stukArrayList = STUKKEN[i][j].getMogelijkeZetten(this, i, j);

                    if (stukArrayList != null) {
                        ALLEMOGELIJKEZETTEN.addAll(stukArrayList);
                    }
                }
            }
        }
        return ALLEMOGELIJKEZETTEN;
    }

    public ArrayList<Zet> getAlleMogelijkeZetten()
	{
		return getAlleMogelijkeZetten(this.beurt);
	}

    public Stuk[][] copyArray() {
        Stuk[][] stukkenCopy = new Stuk[8][8];
        for (int i = 0; i < stukkenCopy.length; i++) {
            for (int j = 0; j < stukkenCopy[i].length; j++) {
                stukkenCopy[i][j] = STUKKEN[i][j];
            }
        }
        return stukkenCopy;
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

    public boolean alleKoningenAanwezig(){
        return !checkKleurHeeftVerloren(true)&&!checkKleurHeeftVerloren(false);
    }
}
