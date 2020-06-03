package com.odin.install.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.install.demo.Constant;
import com.odin.install.demo.R;
import com.odin.install.demo.data.UserRelation;

import java.util.ArrayList;
import java.util.List;

public class UserRelationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserRelation> userRelationList;

    public UserRelationAdapter(List<UserRelation> userRelationList) {
        this.userRelationList = userRelationList;
    }

    public void addUserRelation(List<UserRelation> addListData) {
        if (userRelationList == null) {
            userRelationList = new ArrayList<>();
        }
        userRelationList.addAll(addListData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constant.TYPE_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_relation_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_relation_content, parent, false);
            return new ContentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final UserRelation userRelation = userRelationList.get(position);

        int viewType = getItemViewType(position);
        if (viewType == Constant.TYPE_TITLE) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            viewHolder.mTvTitle.setText(userRelation.getName());
        } else {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            viewHolder.mTvContent.setText(userRelation.getName());
            Context context = viewHolder.itemView.getContext();
            if (viewType == Constant.TYPE_CONTENT_ME) {
                viewHolder.mImgUserIcon.setImageResource(R.mipmap.icon_user_1);
            } else if (viewType == Constant.TYPE_CONTENT_FATHER) {
                viewHolder.mImgUserIcon.setImageResource(R.mipmap.icon_user_2);
            } else {
                viewHolder.mImgUserIcon.setImageResource(R.mipmap.icon_user_3);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (userRelationList == null) {
            return 0;
        }
        return userRelationList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (userRelationList == null) {
            return 0;
        }
        return userRelationList.get(position).getType();
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_user_relation_title);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView mTvContent;
        ImageView mImgUserIcon;

        ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvContent = itemView.findViewById(R.id.tv_user_relation_id);
            mImgUserIcon = itemView.findViewById(R.id.img_user_relation);
        }
    }
}
