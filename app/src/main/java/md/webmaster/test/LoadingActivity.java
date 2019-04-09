package md.webmaster.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class LoadingActivity extends AppCompatActivity {
    private Intent intent;
    public Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        handler = new Handler();
        intent = new Intent(getApplicationContext(), LoginActivity.class);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                setResult(RESULT_OK,intent);
                startActivity(intent);

                finish();
            }

        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
      /*  handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        });*/
      /*  handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.ca}},2000
            );*/
    }
}
