package android.ei6.biz.rdvlistemodule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fabien on 18/03/2016.
 */
public class MyListAdapter extends SimpleAdapter {

    LayoutInflater mInflater;
    ArrayList<HashMap<String,String>> donnees ;
    boolean[] checkBoxState;

    public MyListAdapter(Context context,ArrayList<HashMap<String,String>> data,
                         int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mInflater = LayoutInflater.from(context);
        donnees = data;
        checkBoxState = new boolean[data.size()+100];

    }

    public boolean[] getCheckBoxState() {
        return checkBoxState;
    }

    private ViewHolder viewHolder;

    private class ViewHolder {
        TextView telephone, nom, horaire;
        CheckBox check;

    }

    public View getView(final int position, View cv, ViewGroup parent) {
        if(cv == null) {
            cv = mInflater.inflate(R.layout.list_detail,null);

            viewHolder = new ViewHolder();
            viewHolder.telephone = (TextView) cv.findViewById(R.id.tel);
            viewHolder.nom = (TextView) cv.findViewById(R.id.nom);
            viewHolder.horaire = (TextView) cv.findViewById(R.id.heure);
            viewHolder.check = (CheckBox) cv.findViewById(R.id.check);

            cv.setTag(viewHolder);


        }
        else {
            viewHolder = (ViewHolder) cv.getTag();

        }

        viewHolder.nom.setText(donnees.get(position).get("nom").toString());
        viewHolder.telephone.setText(donnees.get(position).get("telephone").toString());
        viewHolder.horaire.setText(donnees.get(position).get("horaire").toString());
        viewHolder.check.setChecked(checkBoxState[position]);

        viewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()) {
                    checkBoxState[position] = true;
                }
                else  {
                    checkBoxState[position] = false;
                }
            }
        });



        return cv;
    }
}
