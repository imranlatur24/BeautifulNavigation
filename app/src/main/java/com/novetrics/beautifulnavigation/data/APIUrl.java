package com.novetrics.beautifulnavigation.data;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUrl {

    public static final String BASE_URL = "http://192.168.0.105:5000/";
    public static final String BASE_IMAGE = "http://sninfo.online/Employee/";
    public static final String BASE_MERCHANT = "http://sninfo.online/Merchant/";
    public static final String KEY = "123456789";


    public static Retrofit retrofit = null;


    public static Retrofit getClient() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
