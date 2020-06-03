package com.odin.install.demo.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.R;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.listener.AppWakeUpAdapter;
import com.odin.odininstall.model.AppData;

/**
 * 一键唤醒
 */
public class OneKeyWakeupActivity extends BaseSchemeActivity {

    private static final String TAG = "OneKeyWakeupActivity";

    private AppWakeUpAdapter wakeUpAdapter = new AppWakeUpAdapter() {

        @Override
        public void onWakeUp(AppData appData) {
            //获取渠道数据
            String channelCode = appData.getChannel();
            //获取绑定数据
            String bindData = appData.getData();
            Log.d(TAG, "channelCode: " + channelCode + ", wakeupData = " + appData.toString());
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_key_wakeup;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_one_key_wakeup);
    }

    @Override
    public void initView() {
        super.initView();
        //获取唤醒参数
        OdinInstall.getWakeUp(getIntent(), wakeUpAdapter);
    }

    /**
     * 当App处于后台，唤醒时会调用
     *
     * @param intent 意图
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        OdinInstall.getWakeUp(getIntent(), wakeUpAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wakeUpAdapter = null;
    }

    /**
     * 一键分享：分享一个链接，实现Web与App的无缝链接
     */
    public void immediateUse(View view) {
        GlobalUtil.oneKeyShare();
    }
}
