package com.odin.install.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.R;
import com.odin.install.demo.utils.GlobalUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 被还原的场景的Activity
 */
public class RestoreSceneActivity extends BaseSchemeActivity {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_CONTENT = "EXTRA_CONTENT";
    private static final String EXTRA_SHARE_URL = "EXTRA_SHARE_URL";

    public static void newInstance(Context context, String title, String content, String shareUrl) {
        Intent intent = new Intent(context, RestoreSceneActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CONTENT, content);
        intent.putExtra(EXTRA_SHARE_URL, shareUrl);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_restore_scene;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_restore_scene);
    }

    @Override
    public void onClickHeaderBack() {
        startToActivity(ScenarioReductionActivity.class);
        finish();
    }

    @Override
    public void initView() {
        super.initView();
        TextView mTvTitle = findViewById(R.id.tv_restore_title);
        TextView mTvTime = findViewById(R.id.tv_restore_time);
        TextView mTvContent = findViewById(R.id.tv_restore_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE);
        String content = intent.getStringExtra(EXTRA_CONTENT);
        final String shareUrl = intent.getStringExtra(EXTRA_SHARE_URL);
        mTvTitle.setText(title == null ? "" : title);
        mTvContent.setText(content == null ? "" : content);
        mTvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));

        ImageView mImgShare = findViewById(R.id.img_header_restore_share);
        mImgShare.setOnClickListener(v -> GlobalUtil.oneKeyShare(shareUrl));
    }
}