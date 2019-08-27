/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */


/*TODO
 * V0.57
 * Feedback 27-8 moet nog gedeeltelijk verwerkt worden (gedeelte over eerste AI en Speler Interface) (laatste twee alinias)
 * Methode updateMogelijkeZetten voor de volgende stukken:
 * Pion (afmaken: promoveren + twee plekken vooruit op eerste rij)
 * Toren
 * Paard
 * Loper
 * Koningin
 * Speciale regels (promoveren/rokeren)
 * */
public class main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();

    }
}
