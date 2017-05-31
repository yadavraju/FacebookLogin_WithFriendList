package com.raju.facebookproject.data.service;

import com.raju.facebookproject.data.model.FriendsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface FacebookFriendList {
    @GET("v2.4/{user_id}/taggable_friends")
    Call<FriendsListResponse> getFriendsList(@Path("user_id") String userId,
                                             @Query("access_token") String accessToken,
                                             @Query("limit") int limit,
                                             @Query("after") String afterPage);
}
