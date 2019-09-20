

public class AISteven implements Speler
{
	public static final Class[] PIECES = new Class[]{Koning.class, Koningin.class, Toren.class, Paard.class, Loper.class, Pion.class};
	public static final Score ZERO = new Score(0, 0);

	static class Score implements Comparable<Score>
	{
		public final int points;
		public final int moves;

		public static int points(Stuk stuk)
		{
			// Niet met OO gedaan omdat dan de klasse 'Stuk' moet wijzigen.
			int result = -1;
			for (int index = 0; index < PIECES.length; index++)
			{
				if (stuk.getClass() == PIECES[index])
				{
					result = AISteven.POINTS[index];
				}
			}
			return result;
		}

		public static int points(Speelveld speelveld)
		{
			int result = 0;
			for (int rij = 0; rij < 8; rij++)
			{
				for (int kolom = 0; kolom < 8; kolom++)
				{
					Stuk stuk = speelveld.STUKKEN[rij][kolom];
					if (stuk != null)
					{
						int score = points(stuk);
						if (stuk.KLEUR == speelveld.beurt)
						{
							result += score;
						}
						else
						{
							result -= score;
						}
					}
				}
			}
			return result;
		}

		public Score(int points, int moves)
		{
			this.points = points;
			this.moves = moves;
		}

		public Score(Speelveld speelveld)
		{
			this(points(speelveld), speelveld.getAlleMogelijkeZetten().size());
		}


		@Override
		public int compareTo(Score that)
		{
			int result = Integer.compare(this.points, that.points);
			if (result == 0)
			{
				result = Integer.compare(this.moves, that.moves);
			}
			return result;
		}

		public Score neg()
		{
			return new Score(-this.points, -this.moves);
		}

		@Override
		public String toString()
		{
			return "Score(points: " + this.points + ", moves: " + this.moves + ")";
		}
	}

	public static final int[] POINTS = new int[]{100, 10, 5, 3, 3, 1};
	public final int depth;

	public AISteven(int depth)
	{
		this.depth = depth;
	}


	public Score score(Speelveld speelveld, int depth)
	{
		Zet best = null;
		Score result = ZERO;

		if (0 < depth)
		{
			for (Zet zet : speelveld.getAlleMogelijkeZetten())
			{
				Score scoreBest = score(zet.move(speelveld), depth - 1);
				if (best == null || result.compareTo(scoreBest) < 0)
				{
					best = zet;
					result = scoreBest;

				}
			}
		}
		else
		{
			result = new Score(speelveld);
		}
		return result.neg();
	}

	@Override
	public Zet bepaalVolgendeZet(Speelveld speelveld)
	{
		Zet result = null;
		Score scoreBest = ZERO;
		for (Zet zet : speelveld.getAlleMogelijkeZetten())
		{
			Score score = score(zet.move(speelveld), depth);
			if (result == null || scoreBest.compareTo(score) < 0)
			{
				result = zet;
				scoreBest = score;
			}
		}
		return result;
	}

	@Override
	public String toString()
	{
		return "AISteven";
	}
}
