package com.kamadgiri.dealer.networkReq;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO rest url for java server
public class RetrofitInstanceNative {
    private static Retrofit retrofit;

   /* static {
        System.loadLibrary("native-lib");
    }*/

   // public native String getJavaBaseRestUrl();


   // private String baseUrl = getJavaBaseRestUrl();
    //private String baseUrl = "http://localhost:3031/";
    private String baseUrl = "http://148.72.213.116:3000/";

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)        // 1       120
            .readTimeout(30, TimeUnit.SECONDS)          // 30
            .writeTimeout(15, TimeUnit.SECONDS)         // 15
            .build();

    public Retrofit getRetrofitInstance()
    {
       // int urlValue= SharedPref.getIntURl(MyApplication.getContext(), SharedPref.urlDefaultValue);

       // String  baseUrl2 = baseUrl+urlValue+"/";
        String  baseUrl2 = baseUrl;
//        String  baseUrl2 = baseUrl+"98765/";

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl2)
                    .client(okHttpClient)               //TODO ADD
//                    .baseUrl(ConfigUrls.BASE_URL_REST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit ;
    }
}


