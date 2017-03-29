package com.example.singh.walmartchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.singh.walmartchallenge.model.friendList.Datum;
import com.example.singh.walmartchallenge.model.friendList.Friendlist;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FriendsListActivity extends AppCompatActivity {

    List<Datum> datumList = new ArrayList<>();
    FriendListAdapter adapter;
    private static final String TAG = "FriendListActivity";
    String token = "EAACEdEose0cBAPPOI19Sc3zZAYivzoZAL4fdGltvrc0cyheRiR1ZBWyDL7FAQMlFrIYSLcQ0t7DBq872aemB0iWxYlnET8UZC5dsMcHTybEydshPSMe3XKp5jeYRSyjvazp0vlTMsvf0aa28ojubQc8GZChXkae3ZAbPYjf2x8F4ZAQXY4OwY5aH6q8QZBlJdZBsZD";

    String tokenFromPostman = "EAAacBu47vesBAIdeZAdefIBnqGzbjlhk0B1D7VoQe6iArawpuAyv3zZByquIZBJw5OhV1rkbWZCJmLmZBVQWWgGyVpMXFqLOSkJwUj7aBieW6Q3tDd5WRQ5LDFzTrmc0HVdTFWOsOqN8DMksZCPHzf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Log.d(TAG, "onCreate: " + accessToken.getToken());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvFriendList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new FriendListAdapter(datumList);

        GraphRequest request = GraphRequest.newMyFriendsRequest(
                accessToken,
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        Log.d(TAG, "onCompleted: " + response);
                    }
                });

        request.executeAsync();

        rx.Observable<Friendlist> profileObservable = RetrofitHelper.createObs(String.valueOf(token));
        profileObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Friendlist>() {
                    @Override
                    public void onCompleted() {
                        for (int i = 0; i < datumList.size(); i++) {
                            Log.d(TAG, "onCompleted: " + datumList.get(i).getName());
                        }

                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(Friendlist friendlist) {
                        Log.d(TAG, "onNext: " + friendlist.getSummary().getTotalCount());

                        datumList.addAll(friendlist.getData());


                    }

                });


    }
}
