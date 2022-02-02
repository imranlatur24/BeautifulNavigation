package com.novetrics.beautifulnavigation.activities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.novetrics.beautifulnavigation.data.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class BaseActivity extends AppCompatActivity {
	
	public static SharedPreferenceManager prefManager;
	public DisplayMetrics metrices;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		prefManager = new SharedPreferenceManager(this);
		metrices = getResources().getDisplayMetrics();
	}

	public  boolean isNetworkAvailable()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static String getDateText(String timestamp)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String date = format.format(new Date(Long.parseLong(timestamp)));
		String str = date;
		return str;
	}

	public void errorOut(Throwable t){
		if(!isNetworkAvailable()){
			Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
		}else {
			Toast.makeText(getApplicationContext(), "Server break down, try after sometime ", Toast.LENGTH_LONG).show();
		}
	}
}
