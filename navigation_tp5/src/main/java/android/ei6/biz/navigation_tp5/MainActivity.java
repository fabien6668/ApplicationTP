package android.ei6.biz.navigation_tp5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                Toast.makeText(this,message,Toast.LENGTH_LONG).show();

                break;
        }
    }
}
