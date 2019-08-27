import org.junit.Test;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 27-8-2019
 */
public class KoningTest {

    @Test
    public void updateMogelijkeZetten() {
        Speelveld veld = new Speelveld();
        veld.creerStukken();
        veld.updateAlleMogelijkeZetten();
        veld.move(new Zet(7, 4, 1, 5));
        System.out.println(veld.getStukken()[1][5].updateMogelijkeZetten(veld));
        assert veld.getStukken()[1][5].updateMogelijkeZetten(veld).size() == 8;
    }
}