public class Toernooi
{
	public final Speler[] SPELERS;
	public final int ROUNDS;
	public final int MOVES_RANDOM;

	public Toernooi(Speler[] spelers, int rounds, int movesRandom)
	{
		this.SPELERS = spelers;
		this.ROUNDS = rounds;
		this.MOVES_RANDOM = movesRandom;
	}

	public int run()
	{
		int result = 0;
		for (int round = 0; round < this.ROUNDS; round++)
		{
			Game game;
			Integer winner;
			do
			{
				game = new Game(new Speler[]{AIRandom.AI_RANDOM, AIRandom.AI_RANDOM});
				winner = game.run(this.MOVES_RANDOM);
			}
			while (winner != null);
			game.SPELERS[0] = this.SPELERS[0];
			game.SPELERS[1] = this.SPELERS[1];
			winner = game.run(1000);
			game.winnaar(winner);
			game.current().printVeld();
			System.out.println(new AISteven.Score(game.current()));
			if (winner == null)
			{
				result += 1;
			}
			else
			{
				if (winner == 0)
				{
					result += 2;
				}
			}
		}
		return result;
	}

	public static void main(String[] args)
	{
		Toernooi toernooi = new Toernooi(new Speler[]{new AISteven(2), new AISteven(1)}, 20, 3);
		int score = toernooi.run();
		System.out.println("Wit wint " + 100.0 * score / toernooi.ROUNDS / 2.0 + "% van de punten.");
	}
}
