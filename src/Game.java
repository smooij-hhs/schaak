import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 27-8-2019
 */

/*TODO
 * V0.60
 * Ai slimmer maken
 * */
public class Game {

    private final ArrayList<Speelveld> HISTORY = new ArrayList<>();


    public Game() {

        HISTORY.add(new Speelveld(true));
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();

    }

    public void run() {
        Speler[] speler = new Speler[2];
        Scanner sc = new Scanner(System.in);

        while (speler[0] == null) {
            System.out.println("Is speler één een (AI) of een (Mens)");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("AI")) {
                speler[0] = new AiJaap();
            }
            if (input.equalsIgnoreCase("Mens")) {
                speler[0] = new Mens();
            }
        }

        while (speler[1] == null) {
            System.out.println("Is speler twee een (AI) of een (Mens)");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("AI")) {
                speler[1] = new AiJaap();
            }
            if (input.equalsIgnoreCase("Mens")) {
                speler[1] = new Mens();
            }
        }

        while (!HISTORY.get(HISTORY.size() - 1).checkGameOver()) {
            Speelveld laatsteSpeelveld = HISTORY.get(HISTORY.size() - 1);
            Zet gekozenZet;
            laatsteSpeelveld.printVeld();


            if (laatsteSpeelveld.beurt) {
                System.out.println("Wit is aan de beurt...");
                gekozenZet = speler[0].bepaalVolgendeZet(laatsteSpeelveld);
            } else {
                System.out.println("Zwart is aan de beurt...");
                gekozenZet = speler[1].bepaalVolgendeZet(laatsteSpeelveld);
            }


            // huidigeveld wordt vervangen door een nieuw speelveld waarop de 2d array is overschreven
            HISTORY.add(laatsteSpeelveld.move(gekozenZet));

        }

    }

}
