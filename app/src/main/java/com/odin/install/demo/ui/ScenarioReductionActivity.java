package com.odin.install.demo.ui;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.install.demo.BaseInstallActivity;
import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.Constant;
import com.odin.install.demo.MainActivity;
import com.odin.install.demo.R;
import com.odin.install.demo.adapter.RestoreSceneAdapter;
import com.odin.install.demo.data.RestoreBean;
import com.odin.install.demo.utils.GlobalUtil;

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
    public void onClickHeaderBack() {
        startToActivity(MainActivity.class);
        finish();
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

        String url = getString(R.string.str_share_url_news_detail) + "?" + "odinkey=" + GlobalUtil.getOdinKey();
        if (!TextUtils.isEmpty(GlobalUtil.getChannelCode())) {
            url = url + "&channelCode=" + GlobalUtil.getChannelCode();
        }

        List<RestoreBean> restoreBeanList = new ArrayList<>();

        RestoreBean restoreBean1 = new RestoreBean();
        restoreBean1.setTitle(getString(R.string.str_news_title1));
        restoreBean1.setCount("置顶 专题 1183人浏览");
        restoreBean1.setUrl(url + "&odinData=" + Base64.encodeToString(Constant.SCENARIO_REDUCTION_PAGE_ONE.getBytes(), Base64.DEFAULT));
        restoreBean1.setIcon(R.mipmap.new_1);
        restoreBean1.setContent(getString(R.string.str_news_content1));
        restoreBeanList.add(restoreBean1);

        RestoreBean restoreBean2 = new RestoreBean();
        restoreBean2.setTitle(getString(R.string.str_news_title2));
        restoreBean2.setCount("1142人浏览");
        restoreBean2.setUrl(url + "&odinData=" + Base64.encodeToString(Constant.SCENARIO_REDUCTION_PAGE_SECOND.getBytes(), Base64.DEFAULT));
        restoreBean2.setIcon(R.mipmap.new_2);
        restoreBean2.setContent(getString(R.string.str_news_content2));
        restoreBeanList.add(restoreBean2);

        mRecyclerView.setAdapter(new RestoreSceneAdapter(restoreBeanList));
    }
}