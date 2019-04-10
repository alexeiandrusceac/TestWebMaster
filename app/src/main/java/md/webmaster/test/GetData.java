package md.webmaster.test;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    private static String DATA_URL = "http://start.webpower.cf/test/data/";
    //final String[] array = {"name", "activity", "age", "e-mail", "image"};
    public static ThisClass thisClass;
    private static List<ThisClass> listOfPeople;

    public static ThisClass getData() {

        //listOfPeople = new ArrayList<ThisClass>();
        thisClass = new ThisClass();
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                        HttpGet httpget = new HttpGet(DATA_URL);
                        httpget.setHeader("Content-type", "application/json");

                        InputStream inputStream = null;
                        String result = null;
                        try {
                            HttpResponse response = httpclient.execute(httpget);
                            HttpEntity entity = response.getEntity();

                            inputStream = entity.getContent();
                            // json is UTF-8 by default
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                            StringBuilder sb = new StringBuilder();

                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            result = sb.toString();
                            JSONObject jsonObject = new JSONObject(result);

                            thisClass.name = jsonObject.getString("name");
                            thisClass.activity = jsonObject.getString("activity");
                            thisClass.age = String.valueOf(jsonObject.getInt("age"));
                            thisClass.Email = jsonObject.getString("e-mail");
                            thisClass.image = jsonObject.getString("image");//((new URL(jsonObject.getString("image"))).openConnection()).getInputStream();


                        } catch (Exception e) {
                            e.printStackTrace();
                            // Oops
                        } finally {
                            try {
                                if (inputStream != null) inputStream.close();
                            } catch (Exception squish) {
                            }
                        }
                    }
                });
        thread.start();
        return thisClass;
    }

    public static class TaskAsync extends AsyncTask<ThisClass,ThisClass,ThisClass>{
        private Context cantext;
        public TaskAsync(Context context){
            this.cantext = context;
        }

        @Override
        protected ThisClass doInBackground(ThisClass... thisClasses) {
            return getData();
        }
    }

    public static class ThisClass {

        public String name;
        public String activity;
        public String age;
        public String Email;
        public String image;

        public ThisClass() {
        }
    }
}
