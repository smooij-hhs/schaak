/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 26-8-2019
 */
public class Zet {

    private final int STARTRIJ;
    private final int STARTKOLOM;
    private final int EINDRIJ;
    private final int EINDKOLOM;

    public Zet(int STARTRIJ, int STARTKOLOM, int EINDRIJ, int EINDKOLOM) {
        this.STARTRIJ = STARTRIJ;
        this.STARTKOLOM = STARTKOLOM;
        this.EINDRIJ = EINDRIJ;
        this.EINDKOLOM = EINDKOLOM;
    }

    public int getSTARTRIJ() {
        return STARTRIJ;
    }

    public int getSTARTKOLOM() {
        return STARTKOLOM;
    }

    public int getEINDRIJ() {
        return EINDRIJ;
    }

    public int getEINDKOLOM() {
        return EINDKOLOM;
    }

    public String toString() {
        return "Startrij: " + STARTRIJ + " Startkolom: " + STARTKOLOM + " Eindrij: " + EINDRIJ + " Eindkolom: " + EINDKOLOM + "\n";
    }

    public Speelveld move(Speelveld speelveld) {
        Stuk[][] stukkenCopy = speelveld.copyArray();

        //het stuk op de startPlek van 'stukken' array wordt verplaatst naar de eindplek  bij de 'stukkenCopy' array
        stukkenCopy[getEINDRIJ()][getEINDKOLOM()] = speelveld.STUKKEN[getSTARTRIJ()][getSTARTKOLOM()];

        //verander de interne coordinaten van het verplaatste stuk
        stukkenCopy[getEINDRIJ()][getEINDKOLOM()].setCoordinaten(getEINDRIJ(), getEINDKOLOM());

        //maak de plek leeg in de copy array
        stukkenCopy[getSTARTRIJ()][getSTARTKOLOM()] = null;

        return new Speelveld(speelveld.controleerPromotie(stukkenCopy),!speelveld.beurt);
    }
}
