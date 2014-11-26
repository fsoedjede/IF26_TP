package fr.utt.if26_2014.Model;

import fr.utt.if26_2014.Model.Contact;
import fr.utt.if26_2014.Model.Message;

/**
 * Created by soedjede on 18/11/14.
 */
public class ContactMessage {
    private Contact contact;
    private Message message;

    public ContactMessage() {
    }

    public ContactMessage(Contact contact, Message message) {
        this.contact = contact;
        this.message = message;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
