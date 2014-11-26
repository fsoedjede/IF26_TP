package fr.utt.if26_2014.Model;

import java.util.Date;

/**
 * Created by soedjede on 18/11/14.
 */
public class Message {
    private String message;
    private Date date;
    private Boolean sent;

    public Message() {
    }

    public Message(String message, Date date, Boolean sent) {
        this.message = message;
        this.date = date;
        this.sent = sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
