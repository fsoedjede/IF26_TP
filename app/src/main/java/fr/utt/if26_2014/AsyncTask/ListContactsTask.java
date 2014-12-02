package fr.utt.if26_2014.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.utt.if26_2014.Adapter.ContactsAdapter;
import fr.utt.if26_2014.Model.Contact;
import fr.utt.if26_2014.Model.ContactMessage;
import fr.utt.if26_2014.Model.Message;
import fr.utt.if26_2014.R;
import fr.utt.if26_2014.Rest.Get;
import fr.utt.if26_2014.tools.Prefs;

public class ListContactsTask extends AsyncTask<String, Void, String> {

    private Activity context;

    public ListContactsTask(Activity context) {
        this.context = context;
    }

    protected String doInBackground(String... args) {
        Prefs prefs = new Prefs(context);
        String token = prefs.getMyPrefs("token");
        return Get.get_string("contacts.php" + "?token=" + token);
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
                    ArrayList<ContactMessage> discussion = new ArrayList<ContactMessage>();
                    JSONArray contact_array = (JSONArray) object.get("contacts");
                    for (int i = 0; i < contact_array.length(); i++) {
                        JSONObject details = contact_array.getJSONObject(i);

                        Contact contact = new Contact();
                        Message message = new Message();
                        contact.setId(Integer.valueOf(details.getString("id")));

                        JSONObject contact_object = details.getJSONObject("contact");
                        contact.setFirst_name(contact_object.getString("first_name"));
                        contact.setLast_name(contact_object.getString("last_name"));
                        contact.setEmail(contact_object.getString("email"));

                        JSONObject message_object = details.getJSONObject("message");
                        message.setMessage(message_object.getString("message"));
                        message.setSent(message_object.getBoolean("sent"));

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        message.setDate(formatter.parse(message_object.getString("date")));

                        discussion.add(new ContactMessage(contact, message));
                    }

                    try {
                        ContactsAdapter adapter = new ContactsAdapter(context, R.layout.list_item_discuss);
                        adapter.addMultiple(discussion);
                        ListView lv_contacts = (ListView) context.findViewById(R.id.list_view_contacts);
                        lv_contacts.setAdapter(adapter);
                    } catch (Exception ex) {
                        Toast.makeText(context, "Une erreur s'est produite. Réessayez!", Toast.LENGTH_SHORT).show();
                        ex.printStackTrace();
                    }
                    //Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}