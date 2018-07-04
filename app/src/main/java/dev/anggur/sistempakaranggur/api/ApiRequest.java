package dev.anggur.sistempakaranggur.api;

import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public interface ApiRequest {
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("panggil.php?panggil=login")
    Call<ResponseUser> login (@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("panggil.php?panggil=register")
    Call<ResponseUser> register (@Field("name") String nama,
                                 @Field("password") String password);
}
