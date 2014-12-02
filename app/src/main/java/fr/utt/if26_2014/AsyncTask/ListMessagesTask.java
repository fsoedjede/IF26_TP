package fr.utt.if26_2014.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.utt.if26_2014.Adapter.MessagesAdapter;
import fr.utt.if26_2014.Model.Message;
import fr.utt.if26_2014.R;
import fr.utt.if26_2014.Rest.Get;
import fr.utt.if26_2014.tools.Prefs;


public class ListMessagesTask extends AsyncTask<String, Void, String> {

    private MessagesAdapter adapter = null;

    private Activity context;

    public ListMessagesTask(Activity context) {
        this.context = context;
    }

    protected String doInBackground(String... args) {
        Prefs prefs = new Prefs(context);
        String token = prefs.getMyPrefs("token");
        return Get.get_string("messages.php" + "?token=" + token + "&contact=" + args[0]);
    }

    protected void onPostExecute(String result) {
        //Log.d("json", result);
        if(result == null) {
            Toast.makeText(context, "Impossible de se connecter", Toast.LENGTH_SHORT).show();
        } else {
            try{
                JSONObject object  = new JSONObject(result);
                Boolean error = object.getBoolean("error");
                if (error){
                    Toast.makeText(context, "Impossible de récupérer les informations", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Message> messages = new ArrayList<Message>();
                    JSONArray message_array = (JSONArray) object.get("messages");
                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject details = message_array.getJSONObject(i);

                        Message message = new Message();
                        message.setMessage(details.getString("message"));
                        message.setSent(details.getBoolean("sent"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        message.setDate(formatter.parse(details.getString("date")));

                        messages.add(message);
                        adapter = new MessagesAdapter(context, R.layout.list_item_msg);
                        ListView lv_contacts = (ListView) context.findViewById(R.id.list_view_messages);
                        lv_contacts.setAdapter(adapter);
                    }
                    try {
                        adapter.addMultiple(messages);
                    } catch (Exception ex) {
                        Toast.makeText(context, "Une erreur s'est produite. Réessayez!", Toast.LENGTH_SHORT).show();
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}