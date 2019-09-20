import java.util.ArrayList;
import java.util.Random;

public class AIRandom implements Speler
{
	public static final Random RANDOM = new Random();
	public static final AIRandom AI_RANDOM = new AIRandom();

	private AIRandom()
	{
	}

	public Zet bepaalVolgendeZet(Speelveld speelveld)
	{
		ArrayList<Zet> mogelijkeZettenVoorDezeSpeler = speelveld.getAlleMogelijkeZetten();
		int willekeurigeIndex = RANDOM.nextInt(mogelijkeZettenVoorDezeSpeler.size());
		return mogelijkeZettenVoorDezeSpeler.get(willekeurigeIndex);
	}

	@Override
	public String toString()
	{
		return "AIRandom";
	}

}
