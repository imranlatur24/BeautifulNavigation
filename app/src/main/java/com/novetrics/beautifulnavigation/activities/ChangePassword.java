package com.novetrics.beautifulnavigation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.data.APIService;
import com.novetrics.beautifulnavigation.data.APIUrl;
import com.novetrics.beautifulnavigation.modal.MainModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePassword extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ChangePassword";
    private EditText edt_password,edt_changepassword;
    private Button btnchangepass;
    private String empId,strPasswordCurrent,strPasswordNew;
    private ProgressDialog progressDialog;
    private APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepass_main);
//        getSupportActionBar().setTitle("Change Password");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inti();
        onClickListener();
    }

    private void inti() {

        prefManager.connectDB();
        empId = prefManager.getString("e_id");
        prefManager.closeDB();

        edt_password = (EditText)findViewById(R.id.edt_password);
        edt_changepassword = (EditText)findViewById(R.id.edt_changepassword);
        btnchangepass = (Button) findViewById(R.id.btnchangepass);

        apiService = APIUrl.getClient().create(APIService.class);
    }

    private void onClickListener() {
        btnchangepass.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnchangepass){
            strPasswordCurrent = edt_password.getText().toString().trim();
            strPasswordNew = edt_changepassword.getText().toString().trim();

            if(!isNetworkAvailable()){
                Toast.makeText(ChangePassword.this, "No internet connection", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(strPasswordCurrent)){
                Toast.makeText(ChangePassword.this, "enter New Password", Toast.LENGTH_SHORT).show();
                return;
            } if(strPasswordCurrent.length() < 6){
                Toast.makeText(ChangePassword.this, "enter more than 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(strPasswordNew)){
                Toast.makeText(ChangePassword.this, "enter Confirm password", Toast.LENGTH_SHORT).show();
                return;
            } if(strPasswordNew.length() < 6){
                Toast.makeText(ChangePassword.this, "enter more than 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }if(!strPasswordCurrent.equals(strPasswordNew)){
                Toast.makeText(this, "new password and confirm password does not match", Toast.LENGTH_SHORT).show();
                return;
            }
            changePassword();
        }
    }

    private void changePassword() {
        //defining a progress dialog to show while signing up
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("changing password ...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        //calling the api
        callChangePassword().enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                try{
                    if(200 == 200) {
                        prefManager.connectDB();
                        prefManager.setBoolean("isLogin",false);
                        prefManager.closeDB();
                        Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePassword.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else if(405 == 405){
                        System.out.println(TAG+ " Required Parameter Missing");
                        Toast.makeText(getApplicationContext(), "Error in changing password, try again", Toast.LENGTH_LONG).show();
                    }else {
                        System.out.println(TAG+ " Else Close ");
                        Toast.makeText(getApplicationContext(), "Error in changing password, try again", Toast.LENGTH_LONG).show();
                    }
                }catch (NullPointerException e){
                    Toast.makeText(getApplicationContext(), "Error in editing offer, try after sometime", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                progressDialog.dismiss();
                errorOut(t);
            }
        });
    }

    private Call<MainModel> callChangePassword() {
        return  apiService.callChangePassword(
                "2",
                strPasswordNew);
    }
}

