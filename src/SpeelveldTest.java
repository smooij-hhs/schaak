import org.junit.Test;

import java.util.Random;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 25-8-2019
 */
public class SpeelveldTest {


    @Test
    public void promotieTestWIT() {
        Speelveld veld = new Speelveld();
        veld.getSTUKKEN()[1][1] = new Pion(true, 1, 1);
        veld.printVeld();

        Speelveld veld2 = veld.move(new Zet(1, 1, 0, 1));
        veld2.printVeld();
        veld.printVeld();

        assert veld2.getSTUKKEN()[0][1] instanceof Koningin;

    }

    @Test
    public void promotieTestZWART() {
        Speelveld veld = new Speelveld();
        veld.getSTUKKEN()[6][1] = new Pion(false, 6, 1);
        veld.printVeld();

        Speelveld veld2 = veld.move(new Zet(6, 1, 7, 1));
        veld2.printVeld();

        assert veld2.getSTUKKEN()[7][1] instanceof Koningin;
    }




}