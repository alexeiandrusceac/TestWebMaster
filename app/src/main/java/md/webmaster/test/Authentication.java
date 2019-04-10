package md.webmaster.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AUTH;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authentication {
    private String AUTH_URL = "http://start.webpower.cf/test/auth/";
    private Activity mContext;
    private Boolean succes = false;
    private StringBuilder resultAuthentication = null;
    public Authentication(Activity activity) {
        this.mContext = activity;
    }

    public StringBuilder authentication(final User user) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        HttpURLConnection client = null;
                        try {
                            URL url = new URL(AUTH_URL);
                            client = (HttpURLConnection) url.openConnection();
                            client.setDoOutput(true);
                            client.setDoInput(true);
                            client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            client.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(":pwd".getBytes(), Base64.NO_WRAP));
                            client.setRequestMethod("POST");

                            client.connect();

                            int httpResult = client.getResponseCode();
                            if (httpResult == HttpURLConnection.HTTP_OK) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(
                                        client.getInputStream(), "utf-8"));
                                String line = null;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line + "\n");
                                }
                                resultAuthentication = sb;
                                br.close();
                            }
                        } catch
                        (Exception ex) {
                            ex.printStackTrace();

                        } finally {
                            client.disconnect();
                        }


                    }
                }).start();
        return resultAuthentication;
    }

}
