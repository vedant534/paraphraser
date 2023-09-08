package com.example.minorproject.Network;

import com.example.minorproject.Network.UtteranceAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtteranceAPIService {
    private static String BASE_URL = "https://c7c7-2402-8100-219a-ca74-d92b-ca88-6e37-3591.in.ngrok.io";
    private static Retrofit retrofit;
    private static UtteranceAPI utteranceAPI;

    public static UtteranceAPI getUtteranceAPI(){
        if(retrofit == null){
            initRetrofit();
        }

        return utteranceAPI;
    }

    private static void initRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        utteranceAPI = retrofit.create(UtteranceAPI.class);
    }

}
