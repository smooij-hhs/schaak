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
    private boolean beurt;

    public Game() {
        beurt = true;
        HISTORY.add(new Speelveld());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();

    }

    public void run() {
        Speler[] spelers = new Speler[2];
        Scanner sc = new Scanner(System.in);

        while (spelers[0] == null) {
            System.out.println("Is speler één een (AI) of een (Mens)");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("AI")) {
                spelers[0] = new Speler(true, true);
            }
            if (input.equalsIgnoreCase("Mens")) {
                spelers[0] = new Speler(false, true);
            }
        }

        while (spelers[1] == null) {
            System.out.println("Is speler twee een (AI) of een (Mens)");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("AI")) {
                spelers[1] = new Speler(true, false);
            }
            if (input.equalsIgnoreCase("Mens")) {
                spelers[1] = new Speler(false, false);
            }
        }

        while (!HISTORY.get(HISTORY.size() - 1).checkGameOver()) {
            Speelveld laatsteSpeelveld = HISTORY.get(HISTORY.size() - 1);
            Zet gekozenZet;
            laatsteSpeelveld.printVeld();


            if (beurt) {
                System.out.println("Wit is aan de beurt...");
                gekozenZet = spelers[0].bepaalVolgendeZet(laatsteSpeelveld);
            } else {
                System.out.println("Zwart is aan de beurt...");
                gekozenZet = spelers[1].bepaalVolgendeZet(laatsteSpeelveld);
            }


            // huidigeveld wordt vervangen door een nieuw speelveld waarop de 2d array is overschreven
            HISTORY.add(laatsteSpeelveld.move(gekozenZet));
            beurt = !beurt;
        }
    }

    public String bepaalWinnaar() {
        return HISTORY.get(HISTORY.size() - 1).getWinnaar();
    }
}
