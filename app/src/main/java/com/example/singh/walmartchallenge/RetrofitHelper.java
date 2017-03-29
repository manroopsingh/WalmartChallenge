package com.example.singh.walmartchallenge;

import com.example.singh.walmartchallenge.model.friendList.Friendlist;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by singh on 29-Mar-17.
 */

public class RetrofitHelper {

    private static final String BASE_URL = "https://graph.facebook.com";

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    public static Observable<Friendlist> createObs(String access_token) {
        Retrofit retrofit = create();
        GithubService service = retrofit.create(GithubService.class);
        return service.getListObservable(access_token);
    }


    public interface GithubService {


        //@Header("access_token: EAAacBu47vesBAIdeZAdefIBnqGzbjlhk0B1D7VoQe6iArawpuAyv3zZByquIZBJw5OhV1rkbWZCJmLmZBVQWWgGyVpMXFqLOSkJwUj7aBieW6Q3tDd5WRQ5LDFzTrmc0HVdTFWOsOqN8DMksZCPHzf")
        @GET("/me/friends")
        rx.Observable<Friendlist> getListObservable(@Query("access_token") String access_token);


    }

}
