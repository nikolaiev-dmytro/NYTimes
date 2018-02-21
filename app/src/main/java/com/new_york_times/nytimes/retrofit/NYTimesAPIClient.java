package com.new_york_times.nytimes.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.new_york_times.nytimes.model.MediaList;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 18.02.2018.
 */

public class NYTimesAPIClient {
    private static final String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static Retrofit retrofit = null;


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    private static Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(MediaList.class, new JsonDeserializer<MediaList>() {
            @Override
            public MediaList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.isJsonArray()) {
                    Gson gson = new Gson();
                    return gson.fromJson(json, MediaList.class);
                }
                return null;
            }
        }).create();
    }
}
