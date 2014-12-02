package fr.utt.if26_2014.AsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import fr.utt.if26_2014.ContactsActivity;
import fr.utt.if26_2014.Rest.Get;
import fr.utt.if26_2014.tools.Prefs;

/**
 * Created by soedjede on 26/11/14
 */
public class LoginTask extends AsyncTask<String, Void, String> {

    private Activity context;

    public LoginTask(Activity context) {
        this.context = context;
    }

    protected String doInBackground(String... args) {
        return Get.get_string("login.php" + "?email=" + args[0] + "&password=" + args[1]);
    }

    protected void onPostExecute(String result) {
        if (result == null) {
            Toast.makeText(context, "Impossible de se connecter", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject object = new JSONObject(result);
                Boolean error = object.getBoolean("error");
                if (error) {
                    Toast.makeText(context, "Paramètres de connexion invalides", Toast.LENGTH_SHORT).show();
                } else {
                    Prefs prefs = new Prefs(context);
                    prefs.setMyPrefs("token", object.getString("token"));
                    Intent intent = new Intent(context, ContactsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    context.finish();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
