package fr.utt.if26_2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import fr.utt.if26_2014.AsyncTask.ListContactsTask;
import fr.utt.if26_2014.AsyncTask.ListMessagesTask;
import fr.utt.if26_2014.AsyncTask.SendMessageTask;

public class MessageFeedActivity extends Activity {

    private int other_userid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_feed);

        Intent intent = getIntent();
        other_userid = intent.getIntExtra(ContactsActivity.userid, 0);

        ListMessagesTask mMessagesTask = new ListMessagesTask(this);
        mMessagesTask.execute(String.valueOf(other_userid));

        final EditText et_message = (EditText) findViewById(R.id.et_message);
        ImageButton btn_send = (ImageButton) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = et_message.getText().toString();
                if(msg.length() > 0) {
                    new SendMessageTask(MessageFeedActivity.this).execute(String.valueOf(other_userid), msg);
                    et_message.setText(null);
                    et_message.setHint("Send message");
                    new ListMessagesTask(MessageFeedActivity.this).execute(String.valueOf(other_userid));
                    new ListContactsTask(MessageFeedActivity.this).execute();
                } else {
                    Toast.makeText(MessageFeedActivity.this, "Saisissez votre message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
