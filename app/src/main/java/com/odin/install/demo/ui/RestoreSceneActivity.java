package com.odin.install.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageView;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.R;

/**
 * 被还原的场景的Activity
 */
public class RestoreSceneActivity extends BaseSchemeActivity {

    private WebView mWebView;

    private static final String EXTRA_URL = "EXTRA_URL";

    public static void newInstance(Context context, String url) {
        Intent intent = new Intent(context, RestoreSceneActivity.class);
        intent.putExtra(EXTRA_URL, url);
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
    public void initView() {
        super.initView();
        mWebView = findViewById(R.id.web_view_news);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        } else {
            mWebView.loadUrl("file:///android_asset/news1.htm");
        }
        ImageView mImgShare = findViewById(R.id.img_header_restore_share);
        mImgShare.setOnClickListener(v -> GlobalUtil.oneKeyShare());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}