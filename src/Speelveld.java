import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Speelveld {
    private Stuk[][] stukken = new Stuk[8][8];
    //beurt true = wit is aan zet
    private boolean beurt;
    private boolean gameover;

    //spel wordt voor het eerst opgestart
    public Speelveld() {
        beurt = true;
        creerStukken();
        updateAlleMogelijkeZetten();
        run();
    }

    //toekomsige uitbreiding
    public Speelveld(boolean beurt, Stuk[][] stukken) {
        this.beurt = beurt;
        this.stukken = stukken;
        updateAlleMogelijkeZetten();
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
                ditStuk = getSpeelveld(i, j);
                if (ditStuk == null) {
                    System.out.print(' ');
                }
                if (ditStuk instanceof Pion) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('X');
                    } else {
                        System.out.print('x');
                    }
                }
                if (ditStuk instanceof Paard) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('N');
                    } else {
                        System.out.print('n');
                    }
                }
                if (ditStuk instanceof Toren) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('R');
                    } else {
                        System.out.print('r');
                    }
                }
                if (ditStuk instanceof Loper) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('B');
                    } else {
                        System.out.print('b');
                    }
                }
                if (ditStuk instanceof Koningin) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('Q');
                    } else {
                        System.out.print('q');
                    }
                }
                if (ditStuk instanceof Koning) {
                    if (ditStuk.getKleur() == true) {
                        System.out.print('K');
                    } else {
                        System.out.print('k');
                    }
                }
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

    public void move(int startRij, int startKolom, int eindRij, int eindKolom) {
        stukken[eindRij][eindKolom] = stukken[startRij][startKolom];
        stukken[eindRij][eindKolom].setCoordinaten(eindRij, eindKolom);
        stukken[startRij][startKolom] = null;
        beurt = !beurt;
    }

    public void checkGameOver() {
        boolean koningWit = false;
        boolean koningZwart = false;
        for (int i = 0; i < stukken.length; i++) {
            for (int j = 0; j < stukken[i].length; j++) {
                if (stukken[i][j] instanceof Koning) {
                    if (stukken[i][j].kleur == true) {
                        koningWit = true;
                    }
                    if (stukken[i][j].kleur == false) {
                        koningZwart = true;
                    }
                }
            }
        }
        if (koningWit && !koningZwart) {
            System.out.println("Wit heeft gewonnen");
            gameover = true;
        }
        if (!koningWit && koningZwart) {
            System.out.println("Zwart heeft gewonnen");
            gameover = true;
        }
    }

    public boolean checkSpelerMagGekozenStukBewegen(int rij, int kolom) {
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
        if (stukken[startRij][startKolom].mogelijkeZetten.containsKey(eindRij) && stukken[startRij][startKolom].mogelijkeZetten.containsValue(eindKolom)) {
            return true;
        }
        System.out.println("Dit is geen geldige zet!");
        return false;
    }

    public void run() {

        while (!gameover) {
            printVeld();

            Scanner sc = new Scanner(System.in);
            boolean spelerMagGekozenStukBewegen = false;
            boolean spelerHeeftGeldigeBeurtGemaakt = false;
            int startRij=0;
            int startKolom=0;
            int eindRij=0;
            int eindKolom=0;



            if (beurt == true) {
                System.out.println("Wit is aan de beurt...");
            } else {
                System.out.println("Zwart is aan de beurt...");
            }
            while (!spelerHeeftGeldigeBeurtGemaakt) {
                while (!spelerMagGekozenStukBewegen) {
                    System.out.println("Welk stuk (rij) wil je bewegen?");
                    startRij = sc.nextInt();
                    System.out.println("Welk stuk (kolom) wil je bewegen?");
                    startKolom = sc.nextInt();
                    if (checkSpelerMagGekozenStukBewegen(startRij, startKolom)) {
                        spelerMagGekozenStukBewegen = true;
                    }
                }

                System.out.println("Naar welk veld (rij) wil je dit stuk bewegen?");
                eindRij = sc.nextInt();
                System.out.println("Naar welk veld (kolom wil je dit stuk bewegen?");
                eindKolom = sc.nextInt();
                //deze if uitzetten voor test doeleinden
                if (stukMagNaarGekozenVeld(startRij,startKolom,eindRij, eindKolom)) {
                    spelerHeeftGeldigeBeurtGemaakt = true;
                }
            }

            move(startRij,startKolom,eindRij,eindKolom);
            updateAlleMogelijkeZetten();
            checkGameOver();
        }
    }

    public Stuk getSpeelveld(int rij, int kolom) {
        return stukken[rij][kolom];
    }
    public Stuk[][] getStukken() {
        return stukken;
    }
}
