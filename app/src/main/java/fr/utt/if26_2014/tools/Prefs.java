package fr.utt.if26_2014.tools;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by soedjede on 11/11/14.
 */
public class Prefs {
    private static final String PREFS_NAME = "PrefsIF26";
    public SharedPreferences myPrefs = null;

    public Prefs(Context me) {
        this.myPrefs = me.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setMyPrefs(String key, String value) {
        SharedPreferences.Editor prefsEditor = this.myPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public void removeMyPrefs(String key) {
        SharedPreferences.Editor prefsEditor = this.myPrefs.edit();
        prefsEditor.remove(key);
        prefsEditor.commit();
    }

    public String getMyPrefs(String key){
        return myPrefs.getString(key, null);
    }
}
