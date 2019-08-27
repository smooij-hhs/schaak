import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 27-8-2019
 */
public class Game {
    private Speelveld huidigeVeld;
    private ArrayList<Speelveld> history = new ArrayList<>();
    private ArrayList<Speelveld> mogelijkeSpeelvelden = new ArrayList<>();
    private boolean beurt;

    public Game() {
        beurt=true;
        huidigeVeld=new Speelveld();
        history.add(huidigeVeld);
    }

    public void run() {

        while (!huidigeVeld.checkGameOver()) {
            huidigeVeld.printVeld();

            Scanner sc = new Scanner(System.in);
            boolean spelerMagGekozenStukBewegen = false;
            boolean spelerHeeftGeldigeBeurtGemaakt = false;
            int startRij = 0;
            int startKolom = 0;
            int eindRij = 0;
            int eindKolom = 0;


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
                    if (huidigeVeld.checkSpelerMagGekozenStukBewegen(startRij, startKolom,beurt)) {
                        spelerMagGekozenStukBewegen = true;
                    }
                }

                System.out.println("Naar welk veld (rij) wil je dit stuk bewegen?");
                eindRij = sc.nextInt();
                System.out.println("Naar welk veld (kolom wil je dit stuk bewegen?");
                eindKolom = sc.nextInt();

                if (huidigeVeld.stukMagNaarGekozenVeld(startRij, startKolom, eindRij, eindKolom)) {
                    spelerHeeftGeldigeBeurtGemaakt = true;
                }
            }

            // huidigeveld wordt vervangen door een nieuw speelveld waarop de 2d array is overschreven
            huidigeVeld=new Speelveld(huidigeVeld.move(startRij, startKolom, eindRij, eindKolom));
            history.add(huidigeVeld);
            beurt=!beurt;

            //test
            //System.out.println(history.size());
        }
    }
}
