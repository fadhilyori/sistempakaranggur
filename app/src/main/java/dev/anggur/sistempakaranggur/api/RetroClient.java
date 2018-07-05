package dev.anggur.sistempakaranggur.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class RetroClient {
    private static final String base_url = "http://192.168.1.14/sp-cf/";
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
