package com.odin.install.demo;

import android.widget.TextView;

public abstract class BaseSchemeActivity extends BaseActivity {

    public abstract String getTitleText();

    public abstract void onClickHeaderBack();

    @Override
    public void initView() {
        TextView mTvTitle = findViewById(R.id.tv_header_title);
        String title = getTitleText();
        mTvTitle.setText(title == null ? "" : title);
        findViewById(R.id.img_header_back).setOnClickListener(v -> onClickHeaderBack());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickHeaderBack();
    }
}
