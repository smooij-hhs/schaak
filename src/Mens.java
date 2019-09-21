import java.util.List;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 29-8-2019
 */
public class Mens implements Speler
{
	public Mens()
	{
	}

	public boolean validStart(List<Zet> alleMogelijkeZetten, int rij, int kolom)
	{
		boolean result = false;
		for (Zet zet : alleMogelijkeZetten)
		{
			result |= zet.getSTARTRIJ() == rij && zet.getSTARTKOLOM() == kolom;
		}
		return result;
	}

	public Zet bepaalVolgendeZet(Speelveld speelveld)
	{
		speelveld.printVeld();

		List<Zet> alleMogelijkeZetten = speelveld.getAlleMogelijkeZetten();
		Zet result = null;
		while (result == null)
		{
			int startRij;
			int startKolom;
			do
			{
				System.out.print("Welk stuk (rij) wil je bewegen? ");
				startRij = Game.SCANNER.nextInt();
				System.out.print("Welk stuk (kolom) wil je bewegen? ");
				startKolom = Game.SCANNER.nextInt();
			}
			while (!validStart(alleMogelijkeZetten, startRij, startKolom));
			System.out.print("Naar welk veld (rij) wil je dit stuk bewegen? ");
			int eindRij = Game.SCANNER.nextInt();
			System.out.print("Naar welk veld (kolom wil je dit stuk bewegen? ");
			int eindKolom = Game.SCANNER.nextInt();
			Zet zet = new Zet(startRij, startKolom, eindRij, eindKolom);
			if (alleMogelijkeZetten.contains(zet))
			{
				result = zet;
			}
		}
		return result;
	}

	@Override
	public String toString()
	{
		return "Mens";
	}
}
