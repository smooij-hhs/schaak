import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 25-8-2019
 */
public class SpeelveldTest {

    @Test
    public void checkGameOverGeenZwarteKoning() {
        Speelveld veld = new Speelveld();
        //koning wit staat op 7-4
        //koning zwart staat op 0-4
        veld.move(6,4,0,4);
    }
    @Test
    public void checkGameOverGeenWitteKoning() {
        Speelveld veld = new Speelveld();
        //koning wit staat op 7-4
        //koning zwart staat op 0-4

        //wit verplaatst pion naar voren
        veld.move(6,4,5,4);

        // zwart pion op 1 4 slaat witte koning
        veld.move(1,4,7,4);
    }
}