package config;

/**
 * Created by martin_w on 16.06.2015.
 */

/**
 * Speichern der proDoppik Services zum Unabhängigen ändern der URI
 */
public final class ProDoppikService {

    public static final String restUrl = "http://192.168.0.78:8780/proDoppik/rest/";

    public static final String restStamm = restUrl + "stammdaten/";

    public static final String restStammGetPerson = restStamm + "person?gemeinde=";
}
