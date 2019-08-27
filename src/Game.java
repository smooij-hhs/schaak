import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 27-8-2019
 */
public class Game {

    private ArrayList<Speelveld> history = new ArrayList<>();
    private ArrayList<Speelveld> mogelijkeSpeelvelden = new ArrayList<>();
    private boolean beurt;

    public Game() {
        beurt = true;
        history.add(new Speelveld());
    }

    public void run() {

        while (!history.get(history.size() - 1).checkGameOver()) {
            Speelveld laatsteSpeelveld = history.get(history.size() - 1);
            laatsteSpeelveld.printVeld();

            Scanner sc = new Scanner(System.in);
            boolean spelerMagGekozenStukBewegen = false;
            boolean spelerHeeftGeldigeBeurtGemaakt = false;
            int startRij = 0;
            int startKolom = 0;
            int eindRij = 0;
            int eindKolom = 0;
            Zet dezeZet = new Zet(startRij, startKolom, eindRij, eindKolom);


            if (beurt) {
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

                    if (laatsteSpeelveld.checkSpelerMagGekozenStukBewegen(startRij, startKolom, beurt)) {
                        spelerMagGekozenStukBewegen = true;
                    }
                }

                System.out.println("Naar welk veld (rij) wil je dit stuk bewegen?");
                eindRij = sc.nextInt();
                System.out.println("Naar welk veld (kolom wil je dit stuk bewegen?");
                eindKolom = sc.nextInt();
                dezeZet = new Zet(startRij, startKolom, eindRij, eindKolom);
                if (laatsteSpeelveld.stukMagNaarGekozenVeld(new Zet(startRij, startKolom, eindRij, eindKolom))) {
                    spelerHeeftGeldigeBeurtGemaakt = true;
                }
            }

            // huidigeveld wordt vervangen door een nieuw speelveld waarop de 2d array is overschreven

            history.add(laatsteSpeelveld.move(dezeZet));
            beurt = !beurt;

            //test
            //System.out.println(history.size());
        }
        // hier code schrijven om de winnaar te bepalen (wit/zwart)
    }

    public String bepaalWinaar() {
        return history.get(history.size() - 1).getWinnaar();
    }
}
