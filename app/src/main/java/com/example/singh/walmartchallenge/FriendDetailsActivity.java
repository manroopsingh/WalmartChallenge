package com.example.singh.walmartchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.singh.walmartchallenge.model.profile.FbProfile;
import com.example.singh.walmartchallenge.utility.RetrofitHelper;
import com.facebook.AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendDetailsActivity extends AppCompatActivity {


    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.imgUserFriend)
    ImageView imgUserFriend;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        AccessToken accessToken = AccessToken.getCurrentAccessToken();


        String fields = "work,favorite_athletes,interested_in,languages,hometown,name,link,cover,picture,gender,birthday,location,relationship_status,email,photos";

        rx.Observable<FbProfile> profileObservable = RetrofitHelper.createProfileObs(userId, accessToken.getToken(), fields);
        profileObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FbProfile>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(FbProfile profile) {
                        setValues(profile);
                    }

                });

    }

    public void setValues(FbProfile profile) {
        tvName.setText(profile.getName());
        tvBirthday.setText(profile.getBirthday());
        tvGender.setText(profile.getGender());
        tvEmail.setText(profile.getEmail());
        Glide.with(this).load(profile.getPicture().getData().getUrl()).into(imgUserFriend);
    }

}
