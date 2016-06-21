package misc;

/**
 * Created by martin_w on 16.06.2015.
 */
public class Adresse {
    private String strasse;
    private String nr;
    private String nr_zusatz;
    private String plz;
    private String ort;

    public Adresse(String strasse, String nr, String nr_zusatz, String plz, String ort) {
        this.strasse = strasse;
        this.nr = nr;
        this.nr_zusatz = nr_zusatz;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getNr_zusatz() {
        return nr_zusatz;
    }

    public void setNr_zusatz(String nr_zusatz) {
        this.nr_zusatz = nr_zusatz;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return strasse + " "  + nr + nr_zusatz + "\n" +
                plz + " " + ort;
    }
}
