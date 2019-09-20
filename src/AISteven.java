public class AISteven implements Speler
{
	public static final Class[] PIECES = new Class[]{Koning.class, Koningin.class, Toren.class, Paard.class, Loper.class, Pion.class};
	public static final int[] POINTS = new int[]{100, 10, 5, 3, 3, 1};

	public final int depth;

	public AISteven(int depth)
	{
		this.depth = depth;
	}

	public int score(Stuk stuk)
	{
		// Niet met OO gedaan omdat dan de klasse 'Stuk' moet wijzigen.
		int result = -1;
		for (int index = 0; index < PIECES.length; index++)
		{
			if (stuk.getClass() == PIECES[index])
			{
				result = POINTS[index];
			}
		}
		return result;
	}

	public int score(Speelveld speelveld)
	{
		int result = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Stuk stuk = speelveld.STUKKEN[i][j];
				if (stuk != null && stuk.KLEUR == speelveld.beurt)
				{
					result += score(stuk);
				}
			}

		}
		return result;
	}

	public int score(Speelveld speelveld, int depth)
	{
		Integer result = null;

		if (0 < depth)
		{
			for (Zet zet : speelveld.getAlleMogelijkeZetten())
			{
				int scoreBest = score(zet.move(speelveld), depth - 1);
				if (result == null || result < scoreBest)
				{
					result = scoreBest;
				}
			}
		}
		return -result;
	}

	@Override
	public Zet bepaalVolgendeZet(Speelveld speelveld)
	{
		Zet result = null;
		int scoreBest = 0;
		for (Zet zet : speelveld.getAlleMogelijkeZetten())
		{
			int score = score(zet.move(speelveld), depth - 1);
			if (result == null || scoreBest < score)
			{
				result = zet;
				scoreBest = score;
			}
		}
		return result;
	}
}