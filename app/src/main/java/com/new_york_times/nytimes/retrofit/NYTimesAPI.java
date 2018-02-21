package com.new_york_times.nytimes.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 17.02.2018.
 */

public interface NYTimesAPI {
    @GET("{type}/{section}/{time-period}.json")
    Call<ResultResponse> getResult(@Path("type") String type, @Path("section") String section, @Path("time-period") int period, @Query("api-key") String apiKey);
}
