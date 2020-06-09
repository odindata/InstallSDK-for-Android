package com.odin.install.demo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.Constant;
import com.odin.install.demo.MainActivity;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.R;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.listener.AppWakeUpAdapter;
import com.odin.odininstall.model.AppData;

import org.json.JSONException;
import org.json.JSONObject;

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
            try {
                //根据唤醒传递的参数，可以手动判断，跳转到具体的界面，或者只是唤醒
                JSONObject jsonObject = new JSONObject(bindData);
                String data = jsonObject.optString("data");
                String url = getString(R.string.str_share_url_news_detail) + "?" + "odinkey=" + GlobalUtil.getOdinKey();
                if (!TextUtils.isEmpty(GlobalUtil.getChannelCode())) {
                    url = url + "&channelCode=" + GlobalUtil.getChannelCode();
                }
                if (data.equals(Constant.SCENARIO_REDUCTION_PAGE_ONE)) {
                    RestoreSceneActivity.newInstance(OneKeyWakeupActivity.this,
                            getString(R.string.str_news_title1),
                            getString(R.string.str_news_content1),
                            url + "&odinData=" + Base64.encodeToString(Constant.SCENARIO_REDUCTION_PAGE_ONE.getBytes(), Base64.DEFAULT));
                } else if (data.equals(Constant.SCENARIO_REDUCTION_PAGE_SECOND)) {
                    RestoreSceneActivity.newInstance(OneKeyWakeupActivity.this,
                            getString(R.string.str_news_title2),
                            getString(R.string.str_news_content2),
                            url + "&odinData=" + Base64.encodeToString(Constant.SCENARIO_REDUCTION_PAGE_SECOND.getBytes(), Base64.DEFAULT));
                }
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "channelCode: " + channelCode + ", wakeupData = " + bindData);
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
    public void onClickHeaderBack() {
        startToActivity(MainActivity.class);
        finish();
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
