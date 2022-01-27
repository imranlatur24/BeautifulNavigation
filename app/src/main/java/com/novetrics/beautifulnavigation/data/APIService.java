package com.novetrics.beautifulnavigation.data;


import com.novetrics.beautifulnavigation.modal.MainModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    //The login call
    @FormUrlEncoded
    @POST("user_login")
    Call<MainModel> callLogin(
            @Field("empid") String empid,
            @Field("password") String password);

    //The login call
    @FormUrlEncoded
    @POST("user_register")
    Call<MainModel> callSignup(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("empid") String empid,
            @Field("sci_type") String sci_type,
            @Field("password") String password,
            @Field("cpassword") String cpassword);
}