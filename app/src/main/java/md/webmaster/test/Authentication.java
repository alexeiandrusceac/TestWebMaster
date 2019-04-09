package md.webmaster.test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authentication {
    private String AUTH_URL = "http://start.webpower.cf/test/auth/";
    private Context mContext;
    private Boolean succes = false;

    public Authentication(Context context) {
        this.mContext = context;
    }

    public boolean Authentication(final User user) {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(AUTH_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("nickname", user.Nickname);
                    jsonParam.put("password", user.Password);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                    succes = true;
                    // Toast.makeText(mContext,conn.getResponseMessage(),Toast.LENGTH_SHORT);
                } catch (Exception ex) {
                    ex.printStackTrace();
                   // Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT);

                }
            }}.start();
        return succes;
    }

}
