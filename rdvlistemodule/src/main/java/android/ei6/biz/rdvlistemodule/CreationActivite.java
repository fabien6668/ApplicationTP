package android.ei6.biz.rdvlistemodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by fabien on 18/03/2016.
 */
public class CreationActivite extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        Button btn=(Button) findViewById(R.id.valider);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.valider :
                EditText nom = (EditText) findViewById(R.id.nom);
                EditText tel = (EditText) findViewById(R.id.tel);
                EditText date = (EditText) findViewById(R.id.date);
                EditText heure = (EditText) findViewById(R.id.heure);

                String message = getString(R.string.message_clic);
                message = message.replace("@nom",nom.getText());
                message = message.replace("@tel",tel.getText());
                message = message.replace("@date",date.getText());
                message = message.replace("@heure",heure.getText());

                Intent rIntent = new Intent();

                rIntent.putExtra("nom",nom.getText().toString());
                rIntent.putExtra("telephone",tel.getText().toString());
                rIntent.putExtra("date",date.getText().toString());
                rIntent.putExtra("heure",heure.getText().toString());
                setResult(RESULT_OK, rIntent);
                finish();

                break;
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Intent rIntent = new Intent();
//        setResult(RESULT_CANCELED, rIntent);
//        finish();
//    }
}
