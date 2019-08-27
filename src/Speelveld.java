import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public final class Speelveld {
    private final Stuk[][] stukken;
    private ArrayList<Zet> alleMogelijkeZetten = new ArrayList<>();
    private String winnaar;
    //beurt true = wit is aan zet


    //spel wordt voor het eerst opgestart
    public Speelveld() {
        stukken = new Stuk[8][8];

        /*creerStukken();
        updateAlleMogelijkeZetten();*/
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
                    if (getStukken()[i][j].kleur) {
                        koningWit = true;
                    }
                    if (!getStukken()[i][j].kleur) {
                        koningZwart = true;
                    }
                }
            }
        }
        if (koningWit && !koningZwart) {
            System.out.println("Wit heeft gewonnen");
            winnaar = "Wit";
            return true;
        }
        if (!koningWit && koningZwart) {
            System.out.println("Zwart heeft gewonnen");
            winnaar = "Zwart";
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
                Stuk ditStuk = getStukken()[i][j];
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
                    ArrayList<Zet> stukArrayList= stukken[i][j].updateMogelijkeZetten(this);

                    if (stukArrayList != null) {
                        alleMogelijkeZetten.addAll(stukArrayList);
                    }
                }
            }
        }
    }

    public Speelveld move(Zet zet) {
        Stuk[][] stukkenCopy = stukken.clone();

        //het stuk op de startPlek van 'stukken' array wordt verplaatst naar de eindplek  bij de 'stukkenCopy' array
        stukkenCopy[zet.getEindRij()][zet.getEindKolom()] = stukken[zet.getStartRij()][zet.getStartKolom()];

        //verander de interne coordinaten van het verplaatste stuk
        stukkenCopy[zet.getEindRij()][zet.getEindKolom()].setCoordinaten(zet.getEindRij(), zet.getEindKolom());

        //maak de plek leeg in de copy array
        stukkenCopy[zet.getStartRij()][zet.getStartKolom()] = null;

        return new Speelveld(stukkenCopy);
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

    public boolean stukMagNaarGekozenVeld(Zet zet) {
        for (int i = 0; i < alleMogelijkeZetten.size(); i++) {

            if (alleMogelijkeZetten.get(i).getStartRij() == zet.getStartRij() &&
                    alleMogelijkeZetten.get(i).getStartKolom() == zet.getStartKolom() &&
                    alleMogelijkeZetten.get(i).getEindRij() == zet.getEindRij() &&
                    alleMogelijkeZetten.get(i).getEindKolom() == zet.getEindKolom()) {
                return true;
            }
        }
        System.out.println("Dit is geen geldige zet!");
        return false;
    }

    public Stuk[][] getStukken() {
        return stukken;
    }

    public String getWinnaar() {
        return winnaar;
    }
}
