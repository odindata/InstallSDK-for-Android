package com.odin.install.demo.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.R;
import com.odin.install.demo.adapter.RestoreSceneAdapter;
import com.odin.install.demo.data.RestoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景还原
 */
public class ScenarioReductionActivity extends BaseSchemeActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_scenario_reduction;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_scenario_reduction);
    }

    @Override
    public void initView() {
        super.initView();
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 1;
                outRect.bottom = 1;
            }
        });

        List<RestoreBean> restoreBeanList = new ArrayList<>();

        RestoreBean restoreBean1 = new RestoreBean();
        restoreBean1.setTitle("我是新闻1");
        restoreBean1.setCount(86);
        restoreBean1.setUrl("file:///android_asset/news1.htm");
        restoreBeanList.add(restoreBean1);

        RestoreBean restoreBean2 = new RestoreBean();
        restoreBean2.setTitle("我是新闻2");
        restoreBean2.setCount(112);
        restoreBean2.setUrl("file:///android_asset/news2.htm");
        restoreBeanList.add(restoreBean2);

        RestoreBean restoreBean3 = new RestoreBean();
        restoreBean3.setTitle("我是新闻3");
        restoreBean3.setCount(56);
        restoreBean3.setUrl("file:///android_asset/news3.htm");
        restoreBeanList.add(restoreBean3);

        mRecyclerView.setAdapter(new RestoreSceneAdapter(restoreBeanList));
    }
}