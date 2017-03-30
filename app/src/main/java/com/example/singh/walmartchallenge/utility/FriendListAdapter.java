package com.example.singh.walmartchallenge.utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.singh.walmartchallenge.FriendDetailsActivity;
import com.example.singh.walmartchallenge.R;
import com.example.singh.walmartchallenge.model.friendList.Datum;

import java.util.List;

/**
 * Created by singh on 29-Mar-17.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    List<Datum> friendList;
    Context context;

    public FriendListAdapter(Context context, List<Datum> friendList) {
        this.friendList = friendList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendName;
        private ImageView friendImage;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            friendName = (TextView) itemView.findViewById(R.id.tvFirstName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.llList);
            friendImage = (ImageView) itemView.findViewById(R.id.imgProfile);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Datum friend = friendList.get(position);
        String imageURL = "https://graph.facebook.com/" + friend.getId() + "/picture?type=square";
        holder.friendName.setText(friend.getName());
        Glide.with(context).load(imageURL).into(holder.friendImage);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendDetailsActivity.class);
                intent.putExtra("id", friend.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}
