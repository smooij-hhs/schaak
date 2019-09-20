import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 29-8-2019
 */
public class Mens implements Speler{
    public Mens() {
    }

    public Zet bepaalVolgendeZet(Speelveld speelveld) {
        //logica voor mens
        Scanner sc = new Scanner(System.in);
        boolean spelerMagGekozenStukBewegen = false;
        boolean spelerHeeftGeldigeBeurtGemaakt = false;
        int startRij = 0;
        int startKolom = 0;
        int eindRij = 0;
        int eindKolom = 0;

        speelveld.printVeld();

        while (!spelerHeeftGeldigeBeurtGemaakt) {
            while (!spelerMagGekozenStukBewegen) {
                System.out.println("Welk stuk (rij) wil je bewegen?");
                startRij = sc.nextInt();
                System.out.println("Welk stuk (kolom) wil je bewegen?");
                startKolom = sc.nextInt();

                if (speelveld.checkSpelerMagGekozenStukBewegen(startRij, startKolom,speelveld.beurt)) {
                    spelerMagGekozenStukBewegen = true;
                }
            }

            System.out.println("Naar welk veld (rij) wil je dit stuk bewegen?");
            eindRij = sc.nextInt();
            System.out.println("Naar welk veld (kolom wil je dit stuk bewegen?");
            eindKolom = sc.nextInt();

            if (speelveld.stukMagNaarGekozenVeld(new Zet(startRij, startKolom, eindRij, eindKolom),speelveld.getAlleMogelijkeZetten())) {
                spelerHeeftGeldigeBeurtGemaakt = true;
            }
        }
        return new Zet(startRij, startKolom, eindRij, eindKolom);
    }

    @Override
    public String toString()
    {
        return "Mens";
    }
}
