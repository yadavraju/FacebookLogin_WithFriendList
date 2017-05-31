package com.raju.facebookproject.ui.friendsList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.raju.facebookproject.R;
import com.raju.facebookproject.data.model.FriendItemData;
import com.raju.facebookproject.util.Constants;
import com.squareup.picasso.Picasso;

public class FriendDetailActivity extends AppCompatActivity {

    private TextView fName, femail;
    private ImageView ivUser;
    private FriendItemData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        userData = Constants.USER__DETAIL_DATA;
        intitView();
        SetData();
    }

    public void intitView() {
        fName = (TextView) findViewById(R.id.tv_name);
        femail = (TextView) findViewById(R.id.tv_email);
        ivUser = (ImageView) findViewById(R.id.iv_User);

    }

    public void SetData() {
        fName.setText(userData.getName());
        femail.setText(userData.getName());
        String imageUrl = userData.getPicture();
        if (imageUrl != null) {
            if (imageUrl.trim().length() > 0) {
                Picasso.with(this).load(imageUrl).into(ivUser);
            }
        }
    }
}
