package fr.utt.if26_2014.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.utt.if26_2014.Model.ContactMessage;
import fr.utt.if26_2014.R;

/**
 * Created by soedjede on 18/11/14
 */
public class ContactsAdapter extends ArrayAdapter<ContactMessage>{

    private List<ContactMessage> discussions = new ArrayList<ContactMessage>();

    public ContactsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(ContactMessage object) {
        discussions.add(object);
        super.add(object);
    }

    public void addMultiple(List<ContactMessage> object) {
        discussions = object;
        /*Iterator<ContactMessage> i = object.iterator();
        while (i.hasNext()) {
            super.add(i.next());
        }*/
        for(ContactMessage cm : object){
            super.add(cm);
        }
    }

    public int getCount() {
        return discussions.size();
    }

    public ContactMessage getItem(int index) {
        return discussions.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        boolean type = false;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_discuss, parent, false);
        }

        LinearLayout wrapper = (LinearLayout) row.findViewById(R.id.wrapper);
        ContactMessage cmsg = getItem(position);
        TextView tv_nom = (TextView) row.findViewById(R.id.tv_nom);
        TextView tv_message = (TextView) row.findViewById(R.id.tv_message);
        TextView tv_date = (TextView) row.findViewById(R.id.tv_date);

        tv_nom.setText(cmsg.getContact().getFirst_name() + " " + cmsg.getContact().getLast_name() + " (" + cmsg.getContact().getEmail() + ")");
        tv_message.setText(cmsg.getMessage().getMessage());

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("E dd MMM");
        tv_date.setText(formatter.format(cmsg.getMessage().getDate()));

        type = cmsg.getMessage().getSent();
        //wrapper.setBackgroundColor(row.getContext().getResources().getColor(type ?  R.color.darkblue : R.color.darkgreen));
        wrapper.setGravity(type ? Gravity.START : Gravity.END);
        return row;
    }

}
