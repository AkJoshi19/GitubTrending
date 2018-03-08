package com.gitTrending.main.webApi;


import com.gitTrending.main.modal.GitInfoModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anjanik on 3/8/2018.
 * this class contains the fields and data of api request/response
 */
public interface ApiInterface {

    @GET(ApiClient.TRENDING_REPO_API)
    Call<GitInfoModal> getTopRatedMovies1(@Query("language") String language,
                                          @Query("topic") String topic);


}
