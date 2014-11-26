package fr.utt.if26_2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import fr.utt.if26_2014.AsyncTask.ListContactsTask;
import fr.utt.if26_2014.Model.ContactMessage;


public class ContactsActivity extends Activity {

    public final static String userid = "fr.utt.if26_2014.userid";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView lv_contacts = (ListView) findViewById(R.id.list_view_contacts);
        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactMessage contact_message = (ContactMessage) parent.getAdapter().getItem(position);
                Intent MessageActivity = new Intent(ContactsActivity.this, MessageFeedActivity.class);
                MessageActivity.putExtra(userid, contact_message.getContact().getId());
                startActivity(MessageActivity);
            }
        });

        new ListContactsTask(this).execute();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }*/

}
