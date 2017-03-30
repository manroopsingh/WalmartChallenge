package com.example.singh.walmartchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.singh.walmartchallenge.model.friendList.Datum;
import com.example.singh.walmartchallenge.model.friendList.Friendlist;
import com.facebook.AccessToken;
import com.facebook.Profile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FriendsListActivity extends AppCompatActivity {

    List<Datum> datumList = new ArrayList<>();
    FriendListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Profile userProfile;
    private static final String TAG = "FriendListActivity";

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.imgUserProfile)
    ImageView imgProfile;
    @BindView(R.id.rvFriendList)
    RecyclerView rvFriendList;
    @BindView(R.id.tvFriendsCount)
    TextView tvFriendCount;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvGender)
    TextView tvGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        ButterKnife.bind(this);

        //get access token
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        initializeRvFriendList();
        populateRvFriendList(accessToken);

    }

    private void populateRvFriendList(AccessToken accessToken) {

        //RxJava eases communication back from worker thread using observer-subscriber pattern
        rx.Observable<Friendlist> profileObservable = RetrofitHelper.createListObs(String.valueOf(accessToken.getToken()));
        profileObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Friendlist>() {
                    @Override
                    public void onCompleted() {
                        rvFriendList.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(Friendlist friendlist) {
                        setData(friendlist);
                    }
                });
    }

    private void initializeRvFriendList() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvFriendList.setAdapter(adapter);
        rvFriendList.setLayoutManager(layoutManager);
        rvFriendList.setItemAnimator(new DefaultItemAnimator());
        adapter = new FriendListAdapter(this, datumList);
    }

    private void setData(Friendlist friendlist) {
        datumList.addAll(friendlist.getData());
        userProfile = Profile.getCurrentProfile();
        tvFriendCount.setText("Friends (" + friendlist.getSummary().getTotalCount() + ")");
        tvName.setText(userProfile.getName());
        Glide.with(this).load(userProfile.getProfilePictureUri(50, 50)).into(imgProfile);


    }
}
