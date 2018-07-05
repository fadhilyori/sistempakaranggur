package dev.anggur.sistempakaranggur.api;

import java.util.ArrayList;
import java.util.Map;

import dev.anggur.sistempakaranggur.models.Diagnosa;
import dev.anggur.sistempakaranggur.models.Gejala;
import dev.anggur.sistempakaranggur.models.ResponseKonsultasi;
import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public interface ApiRequest {

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("panggil.php?panggil=login")
    Call<ResponseUser> login (@Field("username") String username,
                                 @Field("password") String password);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("panggil.php?panggil=register")
    Call<ResponseUser> register (@Field("username") String username,
                                @Field("password") String password);

    @GET("panggil.php?panggil=getAllDiagnosa")
    Call<ArrayList<Diagnosa>> getDiagnosa ();

    @GET("panggil.php?panggil=getAllGejala")
    Call<ArrayList<Gejala>> getPertanyaan ();

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("panggil.php?panggil=submitKonsultasi")
    Call<ArrayList<ResponseKonsultasi>> konsultasi (@Field("gejala") String gejala);
}
