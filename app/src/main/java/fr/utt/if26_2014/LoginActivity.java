package fr.utt.if26_2014;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26_2014.AsyncTask.LoginTask;

public class LoginActivity extends Activity {

    EditText ET_email = null;
    EditText ET_password = null;
    Button btn_login = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Prefs prefs = new Prefs(getApplicationContext());
        if(prefs.getMyPrefs("token")!= null) {
            Intent intent = new Intent(LoginActivity.this, ContactsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
            finish();
        }*/

        setContentView(R.layout.activity_login);

        ET_email = (EditText) findViewById(R.id.ET_email);
        ET_password = (EditText) findViewById(R.id.ET_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        ET_email.setText("test1@test.fr");
        ET_password.setText("test");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask(LoginActivity.this).execute(ET_email.getText().toString(), ET_password.getText().toString());
            }
        });
    }

}
