/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public final class Speelveld {
    private final Stuk[][] stukken;
    //beurt true = wit is aan zet


    //spel wordt voor het eerst opgestart
    public Speelveld() {
        stukken = new Stuk[8][8];
        creerStukken();
        updateAlleMogelijkeZetten();
    }

    public Speelveld(Stuk[][] stukken) {
        this.stukken = stukken;
        updateAlleMogelijkeZetten();
    }

    public boolean checkGameOver() {
        boolean koningWit = false;
        boolean koningZwart = false;
        for (int i = 0; i < getStukken().length; i++) {
            for (int j = 0; j < getStukken()[i].length; j++) {
                if (getStukken()[i][j] instanceof Koning) {
                    if (getStukken()[i][j].kleur == true) {
                        koningWit = true;
                    }
                    if (getStukken()[i][j].kleur == false) {
                        koningZwart = true;
                    }
                }
            }
        }
        if (koningWit && !koningZwart) {
            System.out.println("Wit heeft gewonnen");
            return true;
        }
        if (!koningWit && koningZwart) {
            System.out.println("Zwart heeft gewonnen");
            return true;
        }
        return false;
    }

    public void creerStukken() {
        //alle zwarte stukken
        stukken[0][0] = new Toren(false, 0, 0);
        stukken[0][1] = new Paard(false, 0, 1);
        stukken[0][2] = new Loper(false, 0, 2);
        stukken[0][3] = new Koningin(false, 0, 3);
        stukken[0][4] = new Koning(false, 0, 4);
        stukken[0][5] = new Loper(false, 0, 5);
        stukken[0][6] = new Paard(false, 0, 6);
        stukken[0][7] = new Toren(false, 0, 7);
        //pionen
        for (int i = 0; i < 8; i++) {
            stukken[1][i] = new Pion(false, 1, i);
            stukken[6][i] = new Pion(true, 6, i);
        }
        //alle witte stukken
        stukken[7][0] = new Toren(true, 7, 0);
        stukken[7][1] = new Paard(true, 7, 1);
        stukken[7][2] = new Loper(true, 7, 2);
        stukken[7][3] = new Koningin(true, 7, 3);
        stukken[7][4] = new Koning(true, 7, 4);
        stukken[7][5] = new Loper(true, 7, 5);
        stukken[7][6] = new Paard(true, 7, 6);
        stukken[7][7] = new Toren(true, 7, 7);
    }

    public void printVeld() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Stuk ditStuk = null;
                ditStuk = getStukken()[i][j];
                if (ditStuk == null) {
                    System.out.print(' ');
                } else System.out.print(getStukken()[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void updateAlleMogelijkeZetten() {
        for (int i = 0; i < stukken.length; i++) {
            for (int j = 0; j < stukken[i].length; j++) {
                if (stukken[i][j] != null) {
                    stukken[i][j].updateMogelijkeZetten(this);
                }
            }
        }
    }

    public Stuk[][] move(int startRij, int startKolom, int eindRij, int eindKolom) {
        Stuk[][] stukkenCopy = stukken.clone();

        stukkenCopy[eindRij][eindKolom] = stukken[startRij][startKolom];
        stukkenCopy[eindRij][eindKolom].setCoordinaten(eindRij, eindKolom);
        stukkenCopy[startRij][startKolom] = null;

        return stukkenCopy;
    }

    public boolean checkSpelerMagGekozenStukBewegen(int rij, int kolom, boolean beurt) {
        if (rij > 7 || rij < 0 || kolom > 7 || kolom < 0) {
            System.out.println("Deze coordinaten vallen buiten het bord");
        } else {
            if (stukken[rij][kolom] == null) {
                System.out.println("Er bevind zich geen stuk op de ingevoerde coordinaten");
                return false;
            }
            if (stukken[rij][kolom].kleur != beurt) {
                System.out.println("De speler mag dit stuk niet bewegen (andere kleur)");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean stukMagNaarGekozenVeld(int startRij, int startKolom, int eindRij, int eindKolom) {
        for (int i = 0; i < stukken[startRij][startKolom].mogelijkeZetten.size(); i++) {
            if (stukken[startRij][startKolom].mogelijkeZetten.get(i).getRij() == eindRij && stukken[startRij][startKolom].mogelijkeZetten.get(i).getKolom() == eindKolom) {
                return true;
            }
        }

        System.out.println("Dit is geen geldige zet!");
        return false;
    }

    public Stuk[][] getStukken() {
        return stukken;
    }
}
