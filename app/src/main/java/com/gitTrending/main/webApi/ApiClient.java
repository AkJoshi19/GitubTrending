package com.gitTrending.main.webApi;

import com.gitTrending.main.appConstant.AppConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anjanik on 3/8/2018.
 */

public class ApiClient {

    /**
     * create singleton for accessing variables
     */
    public static ApiClient mApiClient;
    public static Retrofit retrofit;

    public static ApiClient getClient() {

        if (mApiClient == null) {
            mApiClient = new ApiClient();
        }
        return mApiClient;
    }


    /**
     * get api client request
     *
     * @return
     */
    public Retrofit getRetrofitInstance() {

        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // Can be Level.BASIC, Level.HEADERS, or Level.BODY
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // add logging as last interceptor
            builder.addInterceptor(httpLoggingInterceptor);

            try {
                try {
                    builder.sslSocketFactory(new TLSSocketFactory(), new EasyX509TrustManager(null));
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                }
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(AppConstant.getObj().BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


    public static final String TRENDING_REPO_API = "search/repositories?q=trending";


}