import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 28-8-2019
 */
public class StukTest {

    @Test
    public void updateMogelijkeZettenKONING() {
        Speelveld veld = new Speelveld();
        veld.creerStukken();
        veld.updateAlleMogelijkeZetten();
        veld.move(new Zet(7, 4, 1, 5));
        System.out.println(veld.getStukken()[1][5].updateMogelijkeZetten(veld));
        assert veld.getStukken()[1][5].updateMogelijkeZetten(veld).size() == 8;
    }

    @Test
    public void updateMogelijkeZettenToren() {
        Speelveld veld = new Speelveld();
        veld.creerStukken();
        veld.move(new Zet(7, 7, 4, 4));
        veld.move(new Zet(1,3,4,2));
        veld.move(new Zet(6,3,4,3));
        System.out.println(veld.getStukken()[4][4].updateMogelijkeZetten(veld));
        System.out.println();
        veld.printVeld();
        assert veld.getStukken()[1][5].updateMogelijkeZetten(veld).size() == 5;
    }

    @Test
    public void updateMogelijkeZettenLoper() {
        Speelveld veld = new Speelveld();
        veld.creerStukken();
        veld.move(new Zet(7, 5, 4, 4));
        veld.move(new Zet(1,3,4,2));
        veld.move(new Zet(6,3,3,3));
        System.out.println(veld.getStukken()[4][4].updateMogelijkeZetten(veld));
        System.out.println();
        veld.printVeld();
        assert veld.getStukken()[1][5].updateMogelijkeZetten(veld).size() == 5;
    }

    @Test
    public void updateMogelijkeZettenPaard() {
        Speelveld veld = new Speelveld();
        veld.creerStukken();
        veld.move(new Zet(7, 6, 4, 4));
        veld.move(new Zet(1,3,2,3));
        veld.move(new Zet(6,3,3,2));
        System.out.println(veld.getStukken()[4][4].updateMogelijkeZetten(veld));
        System.out.println();
        veld.printVeld();
        //assert veld.getStukken()[1][5].updateMogelijkeZetten(veld).size() == 5;
    }

}