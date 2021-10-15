package com.example.imagelover.retrofit;

import android.util.Log;

import com.example.imagelover.activity.Photos;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";
    private static final long cacheSize = 5 * 1024 * 1024; // 5 MB
//    Setting up Retrofit and adding HTTPclient to log the data in Run
  private static Retrofit getRetrofit(){

      HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient okHttpClient=new OkHttpClient.Builder()
              .addInterceptor(httpLoggingInterceptor)
              .addNetworkInterceptor(networkInterceptor()) // only used when network is on and will store data in cache
              .addInterceptor(offlineInterceptor())//to show the cache data
              .build();
      Retrofit retrofit=new Retrofit.Builder()
              .baseUrl("https://api.unsplash.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .client(okHttpClient)
              .build();
      return  retrofit;
  }
  public static ApiInterface getInterface(){
      ApiInterface apiInterface=getRetrofit().create(ApiInterface.class);
      return apiInterface;
  }
//  to setup cache size
  private  static Photos photos =new Photos();
    private static Cache cache(){
        return new Cache(new File(photos.getCacheDir(),"someIdentifier"), cacheSize);
    }
//    adding interceptor for online and offline network
    private static Interceptor offlineInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!photos.hasNetwork()) {
                    Log.d("network", "intercept: ");
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }
    private static Interceptor networkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }
}
