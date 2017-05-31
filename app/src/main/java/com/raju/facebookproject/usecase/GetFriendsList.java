package com.raju.facebookproject.usecase;

import com.raju.facebookproject.data.ApiService;
import com.raju.facebookproject.data.model.FriendsListResponse;
import com.raju.facebookproject.data.service.FacebookFriendList;

import retrofit2.Call;
import retrofit2.Callback;



public class GetFriendsList {

   /* public void getTaggableFriends(AccessToken fbToken, GraphRequest.Callback graphRequestCallback) {
        //fbToken return from login with facebook
        GraphRequestAsyncTask r = GraphRequest.newGraphPathRequest(fbToken,
                "/me/taggable_friends", graphRequestCallback
        ).executeAsync();
    }*/

    public void getFBFriendsList(String userId, String accessToken, int limit, String afterPage, Callback<FriendsListResponse> friendsListCallback) {
        FacebookFriendList facebookListService = ApiService.getService().create(FacebookFriendList.class);
        Call<FriendsListResponse> call = facebookListService.getFriendsList(userId, accessToken, limit, afterPage);
        call.enqueue(friendsListCallback);
    }
}
