package com.odin.install.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.install.demo.R;
import com.odin.install.demo.data.RestoreBean;
import com.odin.install.demo.ui.RestoreSceneActivity;

import java.util.List;

public class RestoreSceneAdapter extends RecyclerView.Adapter<RestoreSceneAdapter.RestoreSceneViewHolder> {

    private List<RestoreBean> restoreBeanList;

    public RestoreSceneAdapter(List<RestoreBean> restoreBeanList) {
        this.restoreBeanList = restoreBeanList;
    }

    @NonNull
    @Override
    public RestoreSceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scenario_reduction, parent, false);
        return new RestoreSceneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestoreSceneViewHolder holder, final int position) {
        final RestoreBean restoreBean = restoreBeanList.get(position);
        holder.mTvTitle.setText(restoreBean.getTitle());
        holder.mTvCount.setText(restoreBean.getCount());
        holder.mImgIcon.setImageResource(restoreBean.getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestoreSceneActivity.newInstance(holder.itemView.getContext(), restoreBean.getTitle(), restoreBean.getContent(), restoreBean.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (restoreBeanList == null) {
            return 0;
        }
        return restoreBeanList.size();
    }

    static class RestoreSceneViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        TextView mTvCount;
        ImageView mImgIcon;

        RestoreSceneViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_item_title);
            mTvCount = itemView.findViewById(R.id.tv_item_count);
            mImgIcon = itemView.findViewById(R.id.img_item);
        }
    }
}
