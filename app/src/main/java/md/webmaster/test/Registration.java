package md.webmaster.test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registration {
    public Context mContext;
    private String REG_URL = "http://start.webpower.cf/test/register/";

    public Registration(Context context) {
        this.mContext=context;
    }

    public void registerUser(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(REG_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("nickname", user.Nickname);
                    jsonParam.put("password", user.Password);
                    jsonParam.put("email",user.Email);


                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();

                    Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }).start();

    }
}
