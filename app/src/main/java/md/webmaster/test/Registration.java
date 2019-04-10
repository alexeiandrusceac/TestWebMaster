package md.webmaster.test;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registration {
    public Context mContext;
    private String REG_URL = "http://start.webpower.cf/test/register/";
    private String stringBuilder;
    private  HttpResponse responseHttp;
    private  String response;
    public Registration(Context context) {
        this.mContext = context;
    }

    public String registerUser(final User user) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        final HttpClient client = new DefaultHttpClient();

                        try {
                            URL url = new URL(REG_URL);
                            HttpPost httpPost1 = new HttpPost(String.valueOf(url));
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.connect();

                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("nickname", user.Nickname);
                            jsonParam.put("password", user.Password);
                            jsonParam.put("email", user.Email);

                            StringEntity stringEntity = new StringEntity(jsonParam.toString());
                            stringEntity.setContentEncoding("UTF-8");
                            stringEntity.setContentType("application/json");
                            httpPost1.setEntity(stringEntity);

                            responseHttp = client.execute(httpPost1);
                            BufferedReader rd = new BufferedReader(
                                    new InputStreamReader(responseHttp.getEntity().getContent()));

                            StringBuffer result = new StringBuffer();
                            String line = "";
                            while ((line = rd.readLine()) != null) {
                                result.append(line);
                            }
                            conn.disconnect();
                        } catch (Exception ex) {
                            ex.printStackTrace();

                            Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }).start();
        return response;
    }
}
