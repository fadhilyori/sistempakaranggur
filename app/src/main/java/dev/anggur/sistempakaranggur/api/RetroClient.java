package dev.anggur.sistempakaranggur.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String base_url = "http://192.168.43.249/sp-cf/"; // Sesuiakan dengan IP komputer server
    private static Retrofit retrofit = null;

    private static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiRequest getRequestService(){
        return getClient().create(ApiRequest.class);
    }
}
