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
}
