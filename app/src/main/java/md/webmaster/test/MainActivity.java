package md.webmaster.test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public TableLayout tl;
    public List<GetData.ThisClass> list = new ArrayList<GetData.ThisClass>();
   public GetData.ThisClass clasa;
private ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetData getData = new GetData();


        clasa = getData.getData();
       // AsyncTask<GetData.ThisClass, GetData.ThisClass, GetData.ThisClass> clasa = new GetData.TaskAsync(getApplicationContext()).execute();

        imageview = (ImageView) findViewById(R.id.image);
        //if (clasa != null) {
        try {
            addData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //}
    }

    private void addData() throws MalformedURLException {
        TableLayout stk = (TableLayout) findViewById(R.id.tablelayout);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Name ");
        tv0.setTextSize(20);
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Activity ");
        tv1.setTextSize(20);
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Email ");
        tv2.setTextSize(20);
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Imagine ");
        tv3.setTextSize(20);
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);

        //for (GetData.ThisClass thisClass : list) {
        final TableRow tbrow = new TableRow(this);
        TextView t1v = new TextView(this);
        t1v.setText("" + clasa.name);
        t1v.setPadding(10, 0, 5, 0);
        t1v.setTextColor(Color.BLACK);
        t1v.setTextSize(12);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);
        TextView t2v = new TextView(this);
        t2v.setText(clasa.activity);
        t2v.setTextColor(Color.BLACK);
        t2v.setTextSize(12);
        t2v.setPadding(10, 0, 5, 0);
        t2v.setGravity(Gravity.CENTER);
        tbrow.addView(t2v);
        TextView t3v = new TextView(this);
        t3v.setText(clasa.Email);
        t3v.setPadding(10, 0, 5, 0);
        t3v.setTextColor(Color.BLACK);
        t3v.setTextSize(12);
        t3v.setGravity(Gravity.CENTER);
        tbrow.addView(t3v);

        TextView t4v = new TextView(this);
        t4v.setText(clasa.image);
        t4v.setTextColor(Color.BLACK);
        t4v.setGravity(Gravity.CENTER);
        tbrow.addView(t4v);

        // tbrow.addView(imageView);
        stk.addView(tbrow);

        //Drawable drawable = loadImageFromWebOperations(thisClass.image);
     // final   ImageView imageView = new ImageView(this);
        final Bitmap[] image = new Bitmap[1];
        new Thread(new Runnable() {
               @Override
               public void run() {

                   try {
                   URL url = new URL(clasa.image);
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                   connection.setDoInput(true);
                   connection.connect();
                   InputStream input = connection.getInputStream();
                  image[0] = BitmapFactory.decodeStream(input);

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }).start();

        imageview.setImageBitmap(image[0]);


    }

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("User-agent", "Mozilla/4.0");
            //connection.connect();
            InputStream is = connection.getInputStream();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
