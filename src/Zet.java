/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 26-8-2019
 */
public class Zet {

    int startRij;
    int startKolom;
    int eindRij;
    int eindKolom;

    public Zet(int startRij, int startKolom, int eindRij, int eindKolom) {
        this.startRij = startRij;
        this.startKolom = startKolom;
        this.eindRij = eindRij;
        this.eindKolom = eindKolom;
    }

    public int getStartRij() {
        return startRij;
    }

    public int getStartKolom() {
        return startKolom;
    }

    public int getEindRij() {
        return eindRij;
    }

    public int getEindKolom() {
        return eindKolom;
    }

    public String toString() {
        return "Startrij: " + startRij + " Startkolom: " + startKolom + " Eindrij: " + eindRij + " Eindkolom: " + eindKolom + "\n";
    }
}
