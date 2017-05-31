package com.raju.facebookproject.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raju.facebookproject.data.model.FriendsListResponse;
import com.raju.facebookproject.util.FriendItemDeserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    public static final String BASE_URL = "https://graph.facebook.com/";
    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(FriendsListResponse.class, new FriendItemDeserializer())
            .create();

    public static Retrofit getService() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return retrofit;
    }
}
