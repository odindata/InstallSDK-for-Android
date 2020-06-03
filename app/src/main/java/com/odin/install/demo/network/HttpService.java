package com.odin.install.demo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService {

    private static final String strBaseUrl = "http://www.odinanalysis.com/";

    public static OkHttpClient init() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        okHttpBuilder.addInterceptor(new LoggingInterceptor());
        return okHttpBuilder.build();
    }

    public static <T> T createRetrofit(Class<T> tClass) {
        return new Retrofit.Builder()
                .client(init())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(strBaseUrl)
                .build()
                .create(tClass);
    }
}
