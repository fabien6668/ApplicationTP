package android.ei6.biz.rdvlistemodule;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends ListActivity {

    private ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String,String>>();
    MyListAdapter mSchedule = null;
    private ListView list;
    private HistoryFile fichier;
    private final String TAG="MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = getListView();

        fichier = new HistoryFile(this);

        chargementDeListe();

        mSchedule = new MyListAdapter(list.getContext(),
                                    listItem,
                                    R.layout.list_detail,
                                    new String[] {"telephone","nom","horaire"},
                                    new int[]{R.id.tel,R.id.nom, R.id.heure});

        list.setAdapter(mSchedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        View tb = toolbar.findViewById(R.id.toolbar);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MesPreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<HashMap<String,String>> chargementDeListe()  {

        if(fichier.isFileExist())
        {
            try {

                List<Contacts> donnees = new ArrayList<>();
                for(Contacts c  : donnees) {
                    HashMap<String,String> map = new HashMap<>();

                    map.put("nom",c.getNom());
                    map.put("telephone",c.getTelephone());
                    map.put("horaire", c.getHoraire() );

                    listItem.add(map);
                }

            } catch (Exception e) {
                Toast.makeText(this,"Erreur de chargement des données", Toast.LENGTH_LONG).show();
            }
        }

        return listItem;
    }

    public void afficherLesRappels(View v) {
        StringBuilder buffer = new StringBuilder();
        for( int i =0; i< mSchedule.getCheckBoxState().length; i++){
            if(mSchedule.getCheckBoxState()[i]==true) {
                buffer.append(listItem.get(i).get("nom")+"\n");
            }
        }

        TextView afficheur = (TextView) findViewById(R.id.affichage);
        afficheur.setText(buffer.toString());
    }

    private final int CODE_CREATION=42;
    public void creerRdv(View v) {
        Intent intent =  new Intent(this, CreationActivite.class);
        startActivityForResult(intent, CODE_CREATION);
    }

    @Override
    public void onActivityResult(int requeteCode, int resultCode, Intent donnees) {
        if(requeteCode == CODE_CREATION) {
            if(resultCode==RESULT_OK) {
                String nom = donnees.getStringExtra("nom");
                String tel = donnees.getStringExtra("telephone");
                String date = donnees.getStringExtra("date");
                String heure = donnees.getStringExtra("heure");

                HashMap<String, String> data = new HashMap<>();
                data.put("nom",nom);
                data.put("telephone",tel);
                data.put("horaire",date+"-"+heure);

                mSchedule.donnees.add(data);
                mSchedule.notifyDataSetChanged();


                    //fichier.saveContacts(nom+" "+tel+" "+date+" "+heure);
                    Contacts contacts = new Contacts(nom,date+" "+heure,tel);
                    ContactsCrud cc = new ContactsCrud(this);
                cc.openForWrite();
                cc.insertContact(contacts);
                cc.close();

            }
        }
    }
    public void updateResultsTextfromSms(final List<String> messageenvoi) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (messageenvoi.size() == 0) {
                    Toast.makeText(MainActivity.this, getString(R.string.pas_de_messages), Toast.LENGTH_LONG).show();
                } else {
                    TextView aff = (TextView) findViewById(R.id.affichage);
                    StringBuilder buff = new StringBuilder();
                    for (String s : messageenvoi) {
                        buff.append(s + "\n");
                    }
                    aff.setText(buff.toString());
                }
            }
        });
    }

    public void envoyerSMS(View v) {
        ApiAsyncTask api = new ApiAsyncTask(listItem,mSchedule.checkBoxState,this);
        api.execute();
    }
}
