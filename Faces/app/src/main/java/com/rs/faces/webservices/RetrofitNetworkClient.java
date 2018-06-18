package com.rs.faces.webservices;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rs.faces.app.FacesApp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitNetworkClient {
    private static final String BASE_URL = "https://reqres.in";

    private static Retrofit retrofit = null;

    private static int NETWORK_TIMEOUT = 30;

    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        if (retrofit == null) {

            // Add interceptor to the http client.
            OkHttpClient okHttpClient = okHttpClientBuilder
                    .addNetworkInterceptor(new StethoInterceptor())
                    .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            // Initialize retrofit client.
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(FacesApp.getAppInstance().getGsonInstance()))
                    .client(okHttpClient)
                    .build();

        }
        return retrofit;
    }

    public static void resetRetrofitClient() {
        retrofit = null;
        okHttpClientBuilder = new OkHttpClient.Builder();
    }
}
