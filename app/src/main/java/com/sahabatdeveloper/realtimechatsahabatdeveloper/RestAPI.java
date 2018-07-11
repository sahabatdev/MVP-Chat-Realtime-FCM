package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestAPI {
    //Get Pembayaran
    @GET("tampil")
    Call<Model> getDataChat(@Query("dari") int idDari, @Query("ke") int idKe);

    //Get Inquiry
    @FormUrlEncoded
    @POST("send")
    Call<ModelSend> sendMessage(@Field("dari") int dari, @Field("ke") int ke, @Field("pesan") String pesan);
}
