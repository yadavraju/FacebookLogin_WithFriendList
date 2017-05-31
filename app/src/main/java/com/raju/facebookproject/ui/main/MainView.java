package com.raju.facebookproject.ui.main;

import com.facebook.AccessToken;


public interface MainView {
    void initializeFBSdk();

    void initializeView();

    void showFriendsList();

    void showFBLoginResult(AccessToken fbAccessToken);

    void loginUsingFBManager();
}
