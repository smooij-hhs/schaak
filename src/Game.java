import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 27-8-2019
 */

/*TODO
 * V0.60
 * Ai slimmer maken
 * */
public class Game
{

	private final ArrayList<Speelveld> HISTORY = new ArrayList<>();
	public static final Scanner SCANNER = new Scanner(System.in);
	public final Speler[] SPELERS;

	public void winnaar(Integer winner)
	{
		System.out.println("Winnaar: " + (winner == null ? "gelijkspel" : this.SPELERS[winner].toString()));
	}

	public Game(Speler[] spelers)
	{
		HISTORY.add(new Speelveld(true));
		this.SPELERS = spelers;
	}

	public static Speler select()
	{
		System.out.println("Spelers:");
		for (int index = 0; index < Speler.ALL.length; index++)
		{
			System.out.println(index + ": " + Speler.ALL[index]);
		}
		System.out.print("Select: ");
		int input = SCANNER.nextInt();
		return Speler.ALL[input];
	}

	public static void main(String[] args)
	{
		Speler[] spelers = new Speler[2];
		for (int index = 0; index < 2; index++)
		{
			spelers[index] = select();
		}
		Game game = new Game(spelers);

		Integer winner = game.run(1000);
//		game.winnaar(winner);
	}

	public Speelveld current()
	{
		return HISTORY.get(HISTORY.size() - 1);
	}

	public Integer run(int moves)
	{
		while (current().alleKoningenAanwezig() && 0 < moves)
		{
			Zet gekozenZet;
			//			current().printVeld();

			if (current().beurt)
			{
				//				System.out.println("Wit is aan de beurt...");
				gekozenZet = this.SPELERS[0].bepaalVolgendeZet(current());
			}
			else
			{
				//				System.out.println("Zwart is aan de beurt...");
				gekozenZet = this.SPELERS[1].bepaalVolgendeZet(current());
			}

			// huidigeveld wordt vervangen door een nieuw speelveld waarop de 2d array is overschreven
			HISTORY.add(gekozenZet.move(current()));
			moves -= 1;
		}
		Integer result = null;
		if (current().checkKleurHeeftVerloren(true))
		{
			result = 1;
		}
		if (current().checkKleurHeeftVerloren(false))
		{
			result = 0;
		}
		return result;
	}

}
