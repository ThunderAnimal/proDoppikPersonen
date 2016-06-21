package de.hhberlin.prodoppik.person;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import listView.Item;
import listView.ListItem;
import listView.adapter.TwoTextArrayAdapter;
import misc.Person;
import service.IServiceComplete;
import service.ServiceGetPerson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button mButtonLoadPerson;
    private EditText mTextGemeinde;
    private ProgressBar mProgressBarLoad;
    private ListView mListView;

    private ServiceGetPerson getPerson;

    private AlertDialog.Builder builder;

    private List<Item> items = new ArrayList<Item>();
    private TwoTextArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Objekte mit der GUI Verknüpfen
        getViewElemnts();

        //Adapter für Listview
        setAdapter();

        //Builder für Alert Dialog
        setDialogBuilder();

        //Anzeige für Startview
        setStartView();

        //Events hinzufügen
        setEvents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getViewElemnts(){
        mButtonLoadPerson = (Button) findViewById(R.id.btnloadPersonen);
        mTextGemeinde = (EditText) findViewById(R.id.inputGemeinde);
        mProgressBarLoad = (ProgressBar) findViewById(R.id.progressBarLoadIsbn);
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void setAdapter(){
        adapter = new TwoTextArrayAdapter(this, items);
        mListView.setAdapter(adapter);
    }

    private void setDialogBuilder(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Person Detail");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void setStartView(){
        mProgressBarLoad.setVisibility(View.INVISIBLE);
    }

    private void onClickLoadPerson(){
        String gemeinde = mTextGemeinde.getText().toString();
        if(gemeinde.length() != 0 ){
            executePersonService(gemeinde);
        }
    }

    private void executePersonService(String gemeinde){
        startViewLoad();
        getPerson = new ServiceGetPerson(gemeinde);
        getPerson.setListener(new IServiceComplete() {
            @Override
            public void callback() {
                items.clear();
                for(int i = 0; i < getPerson.getArrayPerson().size(); i++){
                    items.add(new ListItem(getPerson.getArrayPerson().get(i)));
                }
                adapter.notifyDataSetChanged();
                stopViewLoad();
            }
        });
        getPerson.execute();
    }

    private void startViewLoad(){
        mButtonLoadPerson.setEnabled(false);
        mProgressBarLoad.setVisibility(View.VISIBLE);
    }

    private void stopViewLoad(){
        mButtonLoadPerson.setEnabled(true);
        mProgressBarLoad.setVisibility(View.INVISIBLE);
    }

    private void setEvents(){
        mButtonLoadPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoadPerson();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem item;
                Person detailPerson;
                //Prüfen ob ListeItem ausgewählt
                if(items.get(position) instanceof ListItem) {
                    item = (ListItem) items.get(position);
                    //prüfen ob Person ausgewählt
                    if (item.getContent() instanceof Person) {
                        detailPerson = (Person) item.getContent();
                        builder.setMessage(detailPerson.toString());
                        builder.create().show();
                    }
                }
            }
        });
    }


}
