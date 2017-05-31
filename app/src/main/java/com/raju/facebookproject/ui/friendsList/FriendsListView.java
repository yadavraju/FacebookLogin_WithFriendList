package com.raju.facebookproject.ui.friendsList;

import com.raju.facebookproject.data.model.FriendItemData;

import java.util.ArrayList;



public interface FriendsListView {
    void initializeView();

    void loadFriendsList(ArrayList<FriendItemData> friendsList);

    void showError();
}
