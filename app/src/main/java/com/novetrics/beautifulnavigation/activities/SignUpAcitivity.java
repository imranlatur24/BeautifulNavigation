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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.data.APIService;
import com.novetrics.beautifulnavigation.data.APIUrl;
import com.novetrics.beautifulnavigation.modal.MainModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpAcitivity extends BaseActivity  implements View.OnClickListener, OnItemSelectedListener{
    Button btnsignup;
    TextView textview_back_tologin;
    Spinner spinner;
    EditText edt_firstname,edt_lastname,edt_email,edt_password,edt_cpassword;
    String item;
    private static final String TAG = "SignUpAcitivity";
    private String stFirstName,stLastName,stEmail,stPassword,stCPassword,stSpinner;
    private APIService apiService;
    List<String> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_form);

        init();
        onClickListener();
    }

    private void init() {
        if(!checkPermission()){
            requestPermission();
        }
        btnsignup = findViewById(R.id.btnsignup);
        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_cpassword = findViewById(R.id.edt_cpassword);
        textview_back_tologin = findViewById(R.id.textview_back_tologin);
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        categories = new ArrayList<String>();
        categories.add("A");
        categories.add("B");
        categories.add("C");
        categories.add("D");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        apiService = APIUrl.getClient().create(APIService.class);
    }
    private void onClickListener() {
        btnsignup.setOnClickListener(this);
        textview_back_tologin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignup:
                stFirstName = edt_firstname.getText().toString().trim();
                stLastName = edt_lastname.getText().toString().trim();
                stEmail = edt_email.getText().toString().trim();
                stPassword = edt_password.getText().toString().trim();
                stCPassword = edt_cpassword.getText().toString().trim();
                stSpinner =  item;
                if (!isNetworkAvailable()) {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stFirstName)) {
                    Toast.makeText(this, "enter firstname", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stLastName)) {
                    Toast.makeText(this, "enter lastname", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stEmail)) {
                    Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(stEmail)) {
                    Toast.makeText(this, "enter valid email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stSpinner)) {
                    Toast.makeText(this, "select scitiest type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stPassword)) {
                    Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stPassword.length() < 6) {
                    Toast.makeText(this, "enter password should be greater than 6 letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stCPassword)) {
                    Toast.makeText(this, "enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stCPassword.length() < 6) {
                    Toast.makeText(this, "confirm password should be greater than 6 letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!stPassword.equals(stCPassword)){
                    Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                signup(stFirstName,stLastName,stEmail,stSpinner,stPassword,stCPassword);
                break;

            case R.id.textview_back_tologin:
                startActivity(new Intent(SignUpAcitivity.this, LoginActivity.class));
                finish();
        }
    }
    //api calling
    private void signup(final String stFirstName,final String stLastName,final String stEmail,
                        final String stSpinner,
                        final String stPassword,final String stCPassword) {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        //calling the api
        callSignup(stFirstName,stLastName,stEmail,stSpinner,stPassword,stCPassword).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                progressDialog.dismiss();
                try {
                    System.out.println(TAG+ "# Response "+response.body().getResp());
                    System.out.println(TAG+ "# empid "+response.body().getEmpid());
                    System.out.println(TAG+ "# password "+response.body().getPassword());
                    if (200 == 200) {
                        Toast.makeText(getApplicationContext(), "Registration Process Completed", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(SignUpAcitivity.this, LoginActivity.class));
                        finish();
                    } else if (405 == 405) {
                        Toast.makeText(getApplicationContext(), "405", Toast.LENGTH_LONG).show();
                    } else {
                        System.out.println(TAG + " Else Close");
                        Toast.makeText(getApplicationContext(), "other error", Toast.LENGTH_LONG).show();
                    }
                }catch (NullPointerException e){
                    System.out.println(TAG+ " Response "+e.getMessage());
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

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
    private Call<MainModel> callSignup(final String stFirstName,final String stLastName,final String stEmail,
                                       final String stSpinner,
                                       final String stPassword,final String stCPassword) {
        System.out.println("# callSingup: firstname " +stFirstName+" lastname : "+stLastName+" email "+stEmail
        +" password "+stPassword+" cpass "+stCPassword+ " email "+stSpinner);
        return apiService.callSignup(
                stFirstName,
                stLastName,
                stEmail,
                stSpinner,
                stPassword,
                stCPassword
        );
    }

    //permissions
    private void requestPermission() {
        ActivityCompat.requestPermissions(SignUpAcitivity.this, new
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
    //for spinner class
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
