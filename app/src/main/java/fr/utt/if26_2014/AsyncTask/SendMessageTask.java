package fr.utt.if26_2014.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import fr.utt.if26_2014.Rest.Get;
import fr.utt.if26_2014.tools.Prefs;

/**
 * Created by soedjede on 26/11/14.for ${PROJECT}
 */
public class SendMessageTask extends AsyncTask<String, Void, String> {

    private Activity context;

    public SendMessageTask(Activity context) {
        this.context = context;
    }

    protected String doInBackground(String... args) {
        Prefs prefs = new Prefs(context);
        String token = prefs.getMyPrefs("token");
        String other_userid = args[0];
        String sent_msg = args[1];
        return Get.get_string("message.php" + "?token=" + token + "&contact=" + other_userid + "&message=" + sent_msg);
    }

    protected void onPostExecute(String result) {
        if (result == null) {
            Toast.makeText(context, "Impossible de se connecter", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject object = new JSONObject(result);
                Boolean error = object.getBoolean("error");
                if (error) {
                    Toast.makeText(context, "Impossible de récupérer les informations", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Message envoyé", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}