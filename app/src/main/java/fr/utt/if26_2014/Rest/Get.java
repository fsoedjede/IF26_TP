package fr.utt.if26_2014.Rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.utt.if26_2014.Messenger;
import fr.utt.if26_2014.R;

/**
 * @author FELIX
 */
public class Get {

    public static String get_string(String endpoint) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Messenger.getContext().getString(R.string.server_address) + endpoint);
        String json = "";
        try {
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                json += line;
            }
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}
