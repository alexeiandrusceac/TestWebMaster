package md.webmaster.test;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;


public class RegisterActivity extends AppCompatActivity {
    private final AppCompatActivity compatRegisterActivity = RegisterActivity.this;

    private TextInputEditText userNameInputValue;
    private TextInputLayout userNameInputLayout;
    private TextInputEditText workerFirstNameInputValue;
    private TextInputEditText workerFirstNameInputLayout;
    private TextInputEditText workerLastNameInputValue;
    private TextInputLayout workerLastNameInputLayout;
    private TextInputEditText passwordInputValue;

    private TextInputLayout passwordInputLayout;
    private TextInputLayout workerTitleLayout;
    private AppCompatSpinner workerTitleSpinner;
    private TextInputEditText confPasswdInputValue;
    private TextInputLayout confPasswordInputLayout;
    private AppCompatButton registerButton;

    private AppCompatImageView userImageValue;
    private TextInputLayout userBirthdayLayout;
    private TextInputEditText userBirthdayValue;
    private static final int RESULT_GALLERY_IMAGE = 1;
    private static final int RESULT_CAMERA_IMAGE = 0;
    public DatePickerDialog datepicker;
    public String data;
    Intent loginActivity;

    BroadcastReceiver myBroadCastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        initUI();
    }

    void initUI() {
        loginActivity = new Intent(compatRegisterActivity, LoginActivity.class);

        userNameInputLayout = (TextInputLayout) findViewById(R.id.user_name_layout);
        workerLastNameInputValue = (TextInputEditText) findViewById(R.id.worker_prename_text);
        workerLastNameInputLayout = (TextInputLayout) findViewById(R.id.worker_prename_layout);
        passwordInputLayout = (TextInputLayout) findViewById(R.id.user_pass_layout);
        confPasswordInputLayout = (TextInputLayout) findViewById(R.id.user_confpass_layout);
        workerFirstNameInputValue = (TextInputEditText) findViewById(R.id.user_name_text);

        workerTitleSpinner = (AppCompatSpinner) findViewById(R.id.user_title_text);
        passwordInputValue = (TextInputEditText) findViewById(R.id.user_pass_text);
        confPasswdInputValue = (TextInputEditText) findViewById(R.id.user_confpass_text);
        userImageValue = (AppCompatImageView) findViewById(R.id.userImage);
        userBirthdayLayout = (TextInputLayout) findViewById(R.id.user_birth_layout);


        registerButton = (AppCompatButton) findViewById(R.id.register_button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.register_app_toolbar);
        registerButton.setOnClickListener((View.OnClickListener) this);
        userImageValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              //  utils.openImagePopupMenu(userImageValue);
               /* PopupMenu popupMenu = new PopupMenu(compatActivity, userImageValue);
                popupMenu.getMenuInflater().inflate(R.menu.photo_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NewApi")
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.take_photo:
                                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{Manifest.permission.CAMERA}, RESULT_CAMERA_IMAGE);
                                } else {
                                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(cameraIntent, RESULT_CAMERA_IMAGE);
                                }

                                return true;
                            case R.id.choose_photo:
                                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RESULT_GALLERY_IMAGE);
                                return true;
                            case R.id.delete_photo:
                                userImageValue.setImageDrawable(getDrawable(R.drawable.ic_profile2));
                                return true;
                        }
                        Toast.makeText(
                                compatActivity,
                                "Ati selectat: " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popupMenu.show();*/
            }
        });
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(loginActivity);
                finish();
            }
        });
      /*  myBroadCastReceiver = new BroadcastReceiver();
        registerNetworkBroadcast();

    }
    private void registerNetworkBroadcast() {
        registerReceiver(myBroadCastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(myBroadCastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
*/
  //  private void registerUser() {
      /*  if (!valUserData.textFilled(userNameInputValue, userNameInputLayout, getString(R.string.error_name))) {
            return;
        }
        if (!valUserData.textFilled(passwordInputValue, passwordInputLayout, getString(R.string.error_password))) {
            return;
        }
        if (!valUserData.isInputEditTextMatches(passwordInputValue, confPasswdInputValue,
                confPasswordInputLayout, getString(R.string.error_password_match))) {
            return;
        }

        if (!workerDBHelper.checkUserOnLogin(userNameInputValue.getText().toString().trim())) {
            workerData.FirstName = workerFirstNameInputValue.getText().toString();
            workerData.LastName = workerLastNameInputValue.getText().toString();
            workerData.Password = passwordInputValue.getText().toString();
            //workerData.IsOnline = ;
            workerData.Image = utils.convertToByteArray(userImageValue);
            workerData.Birthday = userBirthdayValue.getText().toString();
            workerData.Title = ((Title) (workerTitleSpinner.getSelectedItem())).getTitleIndex();
            workerDBHelper.registerNewWorker(getApplicationContext(), workerData);
            Snackbar.make(scrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            startActivity(loginActivity);
            finish();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(scrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }*/
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterNetworkChanges();
    }

}
