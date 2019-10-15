package com.hast.exchangerate.mvp.models;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Konstantyn Zakharchenko on 24.05.2017.
 */

abstract class BaseModel {
    Retrofit retrofit = null;
    protected Retrofit getApiBuilder(String baseUrl) {
        if (retrofit == null) {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .build();
        } else {
            return retrofit;
        }
    }

    protected OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AmpersandInterceptor())// Added because of NBU has an incorrect query for api which requires a workaround
                .build();
    }

    class AmpersandInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            String stringurl = request.url().toString();
            stringurl = stringurl.replace("%26", "&");

            Request newRequest = new Request.Builder()
                    .url(stringurl)
                    .build();

            return chain.proceed(newRequest);
        }
    }
}
