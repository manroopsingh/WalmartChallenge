package com.example.singh.walmartchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.singh.walmartchallenge.model.friendList.Datum;

import java.util.List;

/**
 * Created by singh on 29-Mar-17.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder>{


    private static final String TAG = "FriendListAdapter";
    List<Datum> friendList;
    Context context;

    public FriendListAdapter(List<Datum> friendList) {
        this.friendList = friendList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendName;
        private ImageView friendImage;
        private LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            friendName = (TextView) itemView.findViewById(R.id.tvFirstName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.llList);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        Datum friend = friendList.get(position);
        String imageURL = "http://graph.facebook.com/" + friend.getId()+ "/picture?type=square";
        Log.d(TAG, "onBindViewHolder: " + imageURL);
        holder.friendName.setText(friend.getName());
        //Glide.with(context).load(imageURL).into(holder.friendImage);


    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}
