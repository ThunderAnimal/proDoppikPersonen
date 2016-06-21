package misc;


import listView.ItemContent;

/**
 * Created by martin_w on 16.06.2015.
 */
public class Person implements ItemContent {
    private String personnr;
    private String name;
    private String vorname;
    private String steuernr;
    private Adresse mAdresse;
    private Kommunikation mKommunikation;

    public Person(String personnr, String name, String vorname, String steuernr, Adresse mAdresse, Kommunikation mKommunikation) {
        this.personnr = personnr;
        this.name = name;
        this.vorname = vorname;
        this.steuernr = steuernr;
        this.mAdresse = mAdresse;
        this.mKommunikation = mKommunikation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getSteuernr() {
        return steuernr;
    }

    public void setSteuernr(String steuernr) {
        this.steuernr = steuernr;
    }

    public Adresse getmAdresse() {
        return mAdresse;
    }

    public void setmAdresse(Adresse mAdresse) {
        this.mAdresse = mAdresse;
    }

    public Kommunikation getmKommunikation() {
        return mKommunikation;
    }

    public void setmKommunikation(Kommunikation mKommunikation) {
        this.mKommunikation = mKommunikation;
    }

    @Override
    public String getShowText1() {
        return personnr;
    }

    @Override
    public String getShowText2() {
        return name + "\t" + vorname;
    }

    @Override
    public String toString() {
        return "Name:\t" + name + "\n" +
                "Vorname:\t" + vorname + "\n\n" +
                "Adresse:\n" + mAdresse.toString() + "\n\n" +
                "Kommunikation:\n" + mKommunikation.toString();
    }
}
