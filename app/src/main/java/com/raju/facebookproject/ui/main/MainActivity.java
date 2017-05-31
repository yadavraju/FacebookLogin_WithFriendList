package com.raju.facebookproject.ui.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.raju.facebookproject.R;
import com.raju.facebookproject.ui.friendsList.FriendsListActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private MainPresenter mainPresenter;
    private TextView tvLoginResult;
    private LoginButton btnFBLogin;
    private Button btnShowFriendsList;
    private Button btnLoginFBLoginManager;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is just used for getting hashkey
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "facebook.example.com.facebookfriendslist",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        // I used model view presenter design pattern
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void initializeFBSdk() {
        //init the callback manager
        callbackManager = CallbackManager.Factory.create();
        btnFBLogin.registerCallback(callbackManager, fbLoginCallback);
        LoginManager.getInstance().registerCallback(callbackManager, fbLoginCallback);
    }

    @Override
    public void initializeView() {
        tvLoginResult = (TextView) findViewById(R.id.tv_LoginResult);
        btnFBLogin = (LoginButton) findViewById(R.id.fbButton);
        btnShowFriendsList = (Button) findViewById(R.id.btn_showFriendsList);
        btnLoginFBLoginManager = (Button) findViewById(R.id.but_LoginFBLoginManager);
        btnShowFriendsList.setOnClickListener(this);
        btnLoginFBLoginManager.setOnClickListener(this);
    }

    @Override
    public void showFriendsList() {
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFBLoginResult(AccessToken fbAccessToken) {
//        btnShowFriendsList.setVisibility(View.VISIBLE);
//        tvLoginResult.setText( getString(R.string.success_login) + "\n"+
//                getString(R.string.user) + fbAccessToken.getUserId() + "\n" +
//                getString(R.string.token) + fbAccessToken.getToken()
//        );

        showFriendsList();
    }

    @Override
    public void loginUsingFBManager() {
        //"user_friends" this will return only the common friends using this app
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this,
                Arrays.asList("public_profile", "user_friends", "email"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //to pass Results to your facebook callbackManager
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_showFriendsList:
                mainPresenter.onShowFriendsListButtonClicked();
                break;
            case R.id.but_LoginFBLoginManager:
                mainPresenter.onLoginUsingFBManagerClicked();
                break;
        }
    }


    private final FacebookCallback fbLoginCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            mainPresenter.onFBLoginSuccess(loginResult);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
}
