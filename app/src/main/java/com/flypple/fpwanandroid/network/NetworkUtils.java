package com.flypple.fpwanandroid.network;

import com.flypple.fpwanandroid.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class NetworkUtils {
    public static final String BASE_URL = "https://www.wanandroid.com";
    public static final String BASE_URL_2 = "https://www.wanandroid.com";

    private static Retrofit sRetrofit;

    private static Api sService;

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new MoreBaseUrlInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();
        sRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        sService = createService(Api.class);
    }

    private static <T> T createService(Class<T> tClass) {
        return sRetrofit.create(tClass);
    }

    public static Api getService() {
        return sService;
    }
}
