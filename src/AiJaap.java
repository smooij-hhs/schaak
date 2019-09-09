import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 9-9-2019
 */
public class AiJaap implements Speler {
    public AiJaap() {

    }

    public Zet bepaalVolgendeZet(Speelveld speelveld,boolean beurt) {
        //logica voor AI
            Random r = new Random();
            ArrayList<Zet> mogelijkeZettenVoorDezeSpeler = bepaalMogelijkZettenVoorSpeler(speelveld,beurt);
            int willekeurigeIndex = r.nextInt(mogelijkeZettenVoorDezeSpeler.size());

            return mogelijkeZettenVoorDezeSpeler.get(willekeurigeIndex);






    }
    private ArrayList<Zet> bepaalMogelijkZettenVoorSpeler(Speelveld speelveld,boolean beurt) {
        ArrayList<Zet> ALLEMOGELIJKEZETTEN = speelveld.updateAlleMogelijkeZetten();
        ArrayList<Zet> mogelijkeZettenVoorDezeSpeler = new ArrayList<>();
        for (int i = 0; i < ALLEMOGELIJKEZETTEN.size() - 1; i++) {
            int startRij = ALLEMOGELIJKEZETTEN.get(i).getSTARTRIJ();
            int startKolom = ALLEMOGELIJKEZETTEN.get(i).getSTARTKOLOM();
            if (speelveld.getSTUKKEN()[startRij][startKolom].getKLEUR() == beurt) {
                mogelijkeZettenVoorDezeSpeler.add(ALLEMOGELIJKEZETTEN.get(i));
            }
        }


        return mogelijkeZettenVoorDezeSpeler;

    }
}
