package fr.utt.if26_2014.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.if26_2014.Model.Message;
import fr.utt.if26_2014.R;

/**
 * Created by soedjede on 18/11/14.for ${PROJECT}
 */
public class MessagesAdapter extends ArrayAdapter<Message> {

    private List<Message> messages = new ArrayList<Message>();

    public MessagesAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Message object) {
        messages.add(object);
        super.add(object);
    }

    public void addMultiple(List<Message> object) {
        messages = object;
        Iterator<Message> i = object.iterator();
        while (i.hasNext()) {
            super.add(i.next());
        }
    }

    public int getCount() {
        return messages.size();
    }

    public Message getItem(int index) {
        return messages.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_msg, parent, false);
        }
        LinearLayout wrapper = (LinearLayout) row.findViewById(R.id.ll_msg_wrapper);

        Message msg = getItem(position);
        TextView tv_msg = (TextView) row.findViewById(R.id.tv_msg);
        tv_msg.setText(msg.getMessage());

        tv_msg.setBackgroundResource(msg.getSent() ? R.drawable.bubble_yellow : R.drawable.bubble_green );
        wrapper.setGravity(msg.getSent() ? Gravity.START : Gravity.END);
        //wrapper.setGravity(msg.getSent() ? Gravity.LEFT : Gravity.RIGHT);
        return row;
    }

}
