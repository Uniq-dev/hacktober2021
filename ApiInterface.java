package com.example.imagelover.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.imagelover.retrofit.Utils.API_KEY;

//To request data from unsplaash and writing query
public interface   ApiInterface {
    @Headers("Authorization: Client-ID "+API_KEY)
    @GET("photos" )
    Call<List<ImageModel>> getAllImages(
        @Query("page") int page,
        @Query("per_page") int per_page
        );
}
