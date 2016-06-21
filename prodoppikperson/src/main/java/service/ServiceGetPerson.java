package service;

import android.os.AsyncTask;
import config.ProDoppikService;
import misc.Adresse;
import misc.Kommunikation;
import misc.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by martin_w on 16.06.2015.
 */
public class ServiceGetPerson extends AsyncTask<Void, Void, String> {
    private final String serviceUrl = ProDoppikService.restStammGetPerson;

    private IServiceComplete mServiceComplete;
    private String gemeinde;

    //Array zum Daten füllen
    ArrayList<Person> personen =  new ArrayList<Person>();


    public ServiceGetPerson(){}

    public ServiceGetPerson(String gemeinde){
        this.setGemeinde(gemeinde);
    }

    public void setGemeinde(String gemeinde){
        this.gemeinde = gemeinde;
    }

    public void setListener(IServiceComplete serviceComplete){
        mServiceComplete = serviceComplete;
    }

    @Override
    protected String doInBackground(Void... params) {
        String json = "";
        String inputLine;
        //JSON vom REST Service einlesen
        try {
            URL url = new URL(serviceUrl +gemeinde);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = in.readLine()) != null){
                json += inputLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);

        //Eingelesenen JSON verarbeiten
        fillPersonArray(json);

        mServiceComplete.callback();
    }

    private void fillPersonArray(String json){
        JSONArray jsonPersonArray;
        JSONObject jsonPersonObject;
        Person myPerson;
        Kommunikation myKommunikation;
        Adresse myAdresse;
        personen.clear();

        try {
            //JSON STRUCTUR {response:{ds_person:{ds_person:{tt_person: []}}}}
            jsonPersonArray = new JSONObject(json).getJSONObject("response").getJSONObject("ds_person").getJSONObject("ds_person").getJSONArray("tt_person");
            for(int i = 0; i < jsonPersonArray.length(); i++){
                jsonPersonObject = jsonPersonArray.getJSONObject(i);
                myAdresse = new Adresse(jsonPersonObject.getString("strassenname"),jsonPersonObject.getString("hausnummer"),jsonPersonObject.getString("hausnummer_z"),jsonPersonObject.getString("plz"),jsonPersonObject.getString("ort"));
                myKommunikation = new Kommunikation(jsonPersonObject.getString("telefonnr"),jsonPersonObject.getString("funknr"),jsonPersonObject.getString("fax"),jsonPersonObject.getString("e_mail"),jsonPersonObject.getString("internet"));
                myPerson = new Person(jsonPersonObject.getString("personennr"),jsonPersonObject.getString("name"),jsonPersonObject.getString("vorname"),jsonPersonObject.getString("pk_steuernummer"),myAdresse, myKommunikation);

                personen.add(myPerson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getArrayPerson(){
        return personen;
    }
}
