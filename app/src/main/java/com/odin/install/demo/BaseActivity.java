package com.odin.install.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        ImmersionBar.with(this).barColor(R.color.colorWhite).statusBarDarkFont(true).transparentNavigationBar().init();
    }

    public abstract void initView();

    public void startToActivity(Class<? extends BaseActivity> toActivity) {
        startActivity(new Intent(this, toActivity));
    }

}
