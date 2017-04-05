package com.freelance.rapaka.tvongo.model.services;


import com.freelance.rapaka.tvongo.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //public static final String BASEURL = BuildConfig.SERVER_URL;
    static String refreshUrl = BuildConfig.APPLICATION_ID;

    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder;

    public static <S> S createService(Class<S> serviceClass) {
        return init(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass,String baseUrl) {
        if(baseUrl!=null && baseUrl.length()>0){
            refreshUrl = baseUrl;
        }else{

            refreshUrl = BuildConfig.APPLICATION_ID;
        }
        return init(serviceClass);
    }

    private static <S> S init(Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            //Logging
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        builder = new Retrofit.Builder()
                .baseUrl(refreshUrl)
                .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.build();

        //for logging
       /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);*/
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}