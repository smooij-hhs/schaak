import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 29-8-2019
 */
public class Speler {

    private final boolean ISAI;
    private final boolean KLEUR;

    public Speler(boolean ISAI, boolean KLEUR) {
        this.ISAI = ISAI;
        this.KLEUR = KLEUR;
    }

    private ArrayList<Zet> bepaalMogelijkZettenVoorSpeler(Speelveld speelveld) {
        ArrayList<Zet> mogelijkeZettenVoorDezeSpeler = new ArrayList<>();
        for (int i = 0; i < speelveld.getALLEMOGELIJKEZETTEN().size() - 1; i++) {
            int startRij = speelveld.getALLEMOGELIJKEZETTEN().get(i).getSTARTRIJ();
            int startKolom = speelveld.getALLEMOGELIJKEZETTEN().get(i).getSTARTKOLOM();
            if (speelveld.getSTUKKEN()[startRij][startKolom].getKLEUR() == KLEUR) {
                mogelijkeZettenVoorDezeSpeler.add(speelveld.getALLEMOGELIJKEZETTEN().get(i));
            }
        }


        return mogelijkeZettenVoorDezeSpeler;

    }


    public Zet bepaalVolgendeZet(Speelveld speelveld) {
        if (ISAI) {//logica voor AI
            Random r = new Random();
            ArrayList<Zet> mogelijkeZettenVoorDezeSpeler = bepaalMogelijkZettenVoorSpeler(speelveld);
            int willekeurigeIndex = r.nextInt(mogelijkeZettenVoorDezeSpeler.size());

            return mogelijkeZettenVoorDezeSpeler.get(willekeurigeIndex);


        } else {
            //logica voor mens

            Scanner sc = new Scanner(System.in);
            boolean spelerMagGekozenStukBewegen = false;
            boolean spelerHeeftGeldigeBeurtGemaakt = false;
            int startRij = 0;
            int startKolom = 0;
            int eindRij = 0;
            int eindKolom = 0;


            while (!spelerHeeftGeldigeBeurtGemaakt) {
                while (!spelerMagGekozenStukBewegen) {
                    System.out.println("Welk stuk (rij) wil je bewegen?");
                    startRij = sc.nextInt();
                    System.out.println("Welk stuk (kolom) wil je bewegen?");
                    startKolom = sc.nextInt();

                    if (speelveld.checkSpelerMagGekozenStukBewegen(startRij, startKolom, KLEUR)) {
                        spelerMagGekozenStukBewegen = true;
                    }
                }

                System.out.println("Naar welk veld (rij) wil je dit stuk bewegen?");
                eindRij = sc.nextInt();
                System.out.println("Naar welk veld (kolom wil je dit stuk bewegen?");
                eindKolom = sc.nextInt();

                if (speelveld.stukMagNaarGekozenVeld(new Zet(startRij, startKolom, eindRij, eindKolom))) {
                    spelerHeeftGeldigeBeurtGemaakt = true;
                }
            }
            return new Zet(startRij, startKolom, eindRij, eindKolom);
        }
    }
}
