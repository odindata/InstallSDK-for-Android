package com.odin.install.demo;

import com.gyf.immersionbar.ImmersionBar;

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).barColor(R.color.colorWhite).statusBarDarkFont(true).init();
        findViewById(R.id.splash_view).postDelayed(new Runnable() {
            @Override
            public void run() {
                startToActivity(MainActivity.class);
                finish();
            }
        }, 3000);
    }
}
