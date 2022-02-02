package com.novetrics.beautifulnavigation.activities;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.data.APIService;
import com.novetrics.beautifulnavigation.data.APIUrl;
import com.novetrics.beautifulnavigation.modal.MainModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity  implements View.OnClickListener {
    Button btnlogin;
    TextView createnewac,txtForgotPassword;
    EditText etemail,edt_mypass;
    private static final String TAG = "LoginActivity";
    private String strPassword, strMobile,customer_id,strEmail;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_main);
        init();
        onClickListener();
    }

    private void init() {
//        if(!checkPermission()){
//            requestPermission();
//        }
        etemail = findViewById(R.id.etemail);
        edt_mypass = findViewById(R.id.edt_mypass);
        btnlogin = findViewById(R.id.btnlogin);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        createnewac = findViewById(R.id.createnewac);
        apiService = APIUrl.getClient().create(APIService.class);
    }
    private void onClickListener() {
        btnlogin.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        createnewac.setOnClickListener(this);
    }

    //api calling
    private void login(final String strEmail,final String strPassword) {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        //calling the api
        callLogin(strEmail,strPassword).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                progressDialog.dismiss();
                try {
//                System.out.println(TAG+ "# Response "+response.body().getMessage());
//                System.out.println(TAG+ "# empid "+response.body().getEmployee_details().get(0).getFirst_name());
                if (200 == 200) {
//                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
//                    prefManager.connectDB();
//                    prefManager.setBoolean("isLogin", true);
//                    prefManager.setString("firstname", response.body().getEmployee_details().get(0).getFirst_name());
//                    prefManager.closeDB();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else if (405 == 405) {
                    Toast.makeText(getApplicationContext(), "Invalid email and password, try again", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(TAG + " Else Close");
                    Toast.makeText(getApplicationContext(), "other error", Toast.LENGTH_LONG).show();
                }
                }catch (NullPointerException e){
                    System.out.println(TAG+ " Response "+e.getMessage());
//                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private Call<MainModel> callLogin(final String strEmail, final String strPassword) {
        System.out.println("# callLogin: " +strEmail+" password: "+strPassword);
        return apiService.callLogin(
                strEmail,
                strPassword
        );
    }


    //onclick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                strEmail = etemail.getText().toString().trim();
                strPassword = edt_mypass.getText().toString().trim();
                if (!isNetworkAvailable()) {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strEmail)) {
                    Toast.makeText(this, "Enter email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(strEmail)) {
                    Toast.makeText(this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strPassword)) {
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strPassword.length() < 6) {
                    Toast.makeText(this, "Enter password should be greater than 6 letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(strEmail, strPassword);
                break;

            case R.id.createnewac:
                startActivity(new Intent(LoginActivity.this, SignUpAcitivity.class));
                finish();
        }
    }

    //permissions
    private void requestPermission() {
        ActivityCompat.requestPermissions(LoginActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE,ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION,CAMERA}, 1);
    }
    public boolean checkPermission() {

        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),
                READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(),
                ACCESS_COARSE_LOCATION);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(),
                ACCESS_FINE_LOCATION);
        int result5 = ContextCompat.checkSelfPermission(getApplicationContext(),
                CAMERA);
        return result1 == PackageManager.PERMISSION_GRANTED
                && result2 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED
                && result4 == PackageManager.PERMISSION_GRANTED
                && result5 == PackageManager.PERMISSION_GRANTED;
    }
}