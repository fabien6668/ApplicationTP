package android.ei6.biz.rdvlistemodule;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fabien on 23/03/2016.
 */
public class ApiAsyncTask extends AsyncTask<Void,Void,Void> {

    ArrayList<HashMap<String,String>> listeContacts;
    boolean[] checkBoxState;
    MainActivity activity;

    public ApiAsyncTask(ArrayList<HashMap<String,String>> liste,boolean[]  check, Activity activity){
        this.listeContacts = liste;
        this.checkBoxState = check;
        this.activity = (MainActivity)activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        activity.updateResultsTextfromSms(sendSms());
        return null;
    }

    private List<String> sendSms() {
        List<String> retval = new ArrayList<>();

        SmsSending ss = new SmsSending(activity);

        for( int i = 0; i<listeContacts.size();i++) {
            if(checkBoxState[i]) {
                retval.add(listeContacts.get(i).get("telephone"));
            }
        }

        return retval;
    }
}
