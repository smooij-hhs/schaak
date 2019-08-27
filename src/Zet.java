/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 26-8-2019
 */
public class Zet {

    int rij;
    int kolom;

    public Zet(int rij, int kolom) {
        this.rij = rij;
        this.kolom = kolom;
    }

    public int getRij() {
        return rij;
    }

    public int getKolom() {
        return kolom;
    }

    public String toString(){return "Rij: "+rij+" Kolom: "+kolom;}
}
