package com.example.singh.walmartchallenge;

import com.example.singh.walmartchallenge.model.friendList.Friendlist;
import com.example.singh.walmartchallenge.model.profile.FbProfile;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    public static Observable<Friendlist> createListObs(String access_token) {
        Retrofit retrofit = create();
        GithubService service = retrofit.create(GithubService.class);
        return service.getListObservable(access_token);
    }

    public static Observable<FbProfile> createProfileObs(String user,String access_token, String fields) {
        Retrofit retrofit = create();
        GithubService service = retrofit.create(GithubService.class);
        return service.getProfileObservable(user,access_token,fields);
    }

    public interface GithubService {

        @GET("/me/friends")
        rx.Observable<Friendlist> getListObservable(@Query("access_token") String access_token);

        @GET("/{user}")
        rx.Observable<FbProfile> getProfileObservable(@Path("user") String user,@Query("access_token") String access_token, @Query("fields") String fields);



    }

}
