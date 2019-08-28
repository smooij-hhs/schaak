import java.util.ArrayList;

/**
 * Created by Jaap van Gestel <18139027@student.hhs.nl> on 21-8-2019
 */
public class Loper extends Stuk {
    public Loper(boolean kleur, int rij, int kolom) {
        super(kleur, rij, kolom);
    }

    @Override
    public ArrayList<Zet> updateMogelijkeZetten(Speelveld veld) {
        ArrayList<Zet> stukArrayList = new ArrayList<>();

        int rijCoordinaat = rij;
        int kolomCoordinaat = kolom;

        //schuinlinksboven

        while (rijCoordinaat>0&&kolomCoordinaat>0){
            if(veld.getStukken()[rijCoordinaat-1][kolomCoordinaat-1]==null){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat-1,kolomCoordinaat-1));
                rijCoordinaat--;
                kolomCoordinaat--;
            }else if(veld.getStukken()[rijCoordinaat-1][kolomCoordinaat-1].kleur!=kleur){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat-1,kolomCoordinaat-1));
                rijCoordinaat=0;
                kolomCoordinaat=0;
            }else {
                rijCoordinaat=0;
                kolomCoordinaat=0;
            }
        }
        rijCoordinaat=rij;
        kolomCoordinaat=kolom;

        //schuinrechtsboven
        while (rijCoordinaat>0&&kolomCoordinaat<7){
            if(veld.getStukken()[rijCoordinaat-1][kolomCoordinaat+1]==null){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat-1,kolomCoordinaat+1));
                rijCoordinaat--;
                kolomCoordinaat++;
            }else if(veld.getStukken()[rijCoordinaat-1][kolomCoordinaat+1].kleur!=kleur){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat-1,kolomCoordinaat+1));
                rijCoordinaat=0;
                kolomCoordinaat=7;
            }else {
                rijCoordinaat=0;
                kolomCoordinaat=7;
            }
        }
        rijCoordinaat=rij;
        kolomCoordinaat=kolom;

        //schuinrechtsonder
        while (rijCoordinaat<7&&kolomCoordinaat<7){
            if(veld.getStukken()[rijCoordinaat+1][kolomCoordinaat+1]==null){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat+1,kolomCoordinaat+1));
                rijCoordinaat++;
                kolomCoordinaat++;
            }else if(veld.getStukken()[rijCoordinaat+1][kolomCoordinaat+1].kleur!=kleur){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat+1,kolomCoordinaat+1));
                rijCoordinaat=7;
                kolomCoordinaat=7;
            }else {
                rijCoordinaat=7;
                kolomCoordinaat=7;
            }
        }
        rijCoordinaat=rij;
        kolomCoordinaat=kolom;

        //schuinlinksonder
        while (rijCoordinaat<7&&kolomCoordinaat>0){
            if(veld.getStukken()[rijCoordinaat+1][kolomCoordinaat-1]==null){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat+1,kolomCoordinaat-1));
                rijCoordinaat++;
                kolomCoordinaat--;
            }else if(veld.getStukken()[rijCoordinaat+1][kolomCoordinaat-1].kleur!=kleur){
                stukArrayList.add(new Zet(rij,kolom,rijCoordinaat+1,kolomCoordinaat-1));
                rijCoordinaat=7;
                kolomCoordinaat=0;
            }else {
                rijCoordinaat=7;
                kolomCoordinaat=0;
            }
        }

        return stukArrayList;
    }

    @Override
    public String toString() {
        if (kleur) {
            return "B";
        } else return "b";
    }
}
