import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 25-8-2019
 */
public class SpeelveldTest {
    Speelveld veld = new Speelveld();

    @Test
    public void checkGameOverGeenZwarteKoning() {

        //koning wit staat op 7-4
        //koning zwart staat op 0-4
        veld.move(6, 4, 0, 4);
    }

    @Test
    public void checkGameOverGeenWitteKoning() {
        //koning wit staat op 7-4
        //koning zwart staat op 0-4

        //wit verplaatst pion naar voren
        veld.move(6, 4, 5, 4);

        // zwart pion op 1 4 slaat witte koning
        veld.move(1, 4, 7, 4);
    }



    @Test
    public void werkingZetObject() {
        for (int i = 6; i > 2; i--) {
            veld.move(i, 3, i - 1, 3);
        }
        veld.getStukken()[2][3].updateMogelijkeZetten(veld);
        veld.printVeld();

        System.out.println(veld.getStukken()[2][3].mogelijkeZetten);
    }

    @Test
    public void werkingHashMap(){
        HashMap<Integer,Integer> test = new HashMap();
        test.put(1,2);
        test.put(1,5);
        test.put(3,5);
        //assert (test.containsKey(1)&&test.containsValue(2));
        assert (test.containsKey(3)&&test.containsValue(5));
        assert (test.containsKey(1)&&test.containsValue(5));

    }

    @Test
    public void werkingArrayClone(){
        int[] a ={1,8,3};
        int[] b =a.clone();

        b[1]=0;
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
        for(int i=0;i<a.length;i++){
            System.out.println(b[i]);
        }

        assert !Arrays.equals(a,b);

    }
}