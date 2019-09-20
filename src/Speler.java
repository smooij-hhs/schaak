/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 9-9-2019
 */
public interface Speler
{
	Speler[] ALL = new Speler[]{new Mens(), new AiJaap(), new AISteven(4)};

	Zet bepaalVolgendeZet(Speelveld speelveld);
}