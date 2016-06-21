package misc;

/**
 * Created by martin_w on 16.06.2015.
 */
public class Kommunikation {
    private String telefon;
    private String mobile;
    private String fax;
    private String email;
    private String internet;

    public Kommunikation(String telefon, String mobile, String fax, String email, String internet) {
        this.telefon = telefon;
        this.mobile = mobile;
        this.fax = fax;
        this.email = email;
        this.internet = internet;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    @Override
    public String toString() {
        return telefon + " " + mobile + "\n" +
                email;
    }
}
