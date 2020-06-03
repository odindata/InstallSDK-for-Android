package com.odin.install.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.gyf.immersionbar.ImmersionBar;
import com.odin.install.demo.ui.ChannelActivity;
import com.odin.install.demo.ui.OneKeyWakeupActivity;
import com.odin.install.demo.ui.ScenarioReductionActivity;
import com.odin.install.demo.ui.UserTraceActivity;
import com.odin.install.demo.ui.dialog.UserNameDialog;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.utils.OdinSpUtil;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.data.ShareData;
import com.odin.odininstall.listener.AppInstallAdapter;
import com.odin.odininstall.listener.AppShareAdapter;
import com.odin.odininstall.model.AppData;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).barColor(R.color.colorWhite).statusBarDarkFont(true).init();
        checkPermission();

        initOdinInstall();
        initOdinInstallShareUrl();
    }

    public void oneKeyWakeup(View view) {
        startToActivity(OneKeyWakeupActivity.class);
    }

    public void scenarioReduction(View view) {
        startToActivity(ScenarioReductionActivity.class);
    }

    public void channel(View view) {
        startToActivity(ChannelActivity.class);
    }

    public void userTrace(View view) {
        startToActivity(UserTraceActivity.class);
    }

    public void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                return;
            }
        }
        registerUserId();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        registerUserId();
    }

    /**
     * 获取通过Install安装携带的参数
     */
    private void initOdinInstall() {
        OdinInstall.getInstall(new AppInstallAdapter() {

            @Override
            public void onInstall(AppData appData) {
                //获取渠道数据
                String channelCode = appData.getChannel();
                //获取自定义数据
                String bindData = appData.getData();
                OdinSpUtil.setString(MainActivity.this, Constant.CHANNEL_CODE, channelCode);
                Log.i(TAG, "Install安装携带的参数，channelCode == " + channelCode + ", bindData == " + bindData);
            }
        });
    }

    /**
     * 如果未注册，弹出注册的对话框
     */
    private void registerUserId() {
        if (TextUtils.isEmpty(GlobalUtil.getUserId())) {
            new UserNameDialog(this).show();
        }
    }

    /**
     * 获取install分享的URL地址
     */
    private void initOdinInstallShareUrl() {
        String userId = GlobalUtil.getUserId();
        String shareUrl = GlobalUtil.getShareUrl();
        if (TextUtils.isEmpty(userId) || !TextUtils.isEmpty(shareUrl)) {
            return;
        }
        OdinInstall.getShareUrl(userId, "test", new AppShareAdapter() {
            @Override
            public void onShareUrl(ShareData shareData) {
                if (shareData == null) {
                    Log.i(TAG, "获取分享链接失败，shareData == null");
                    return;
                }
                String landingPageUrl = shareData.getLandingPageUrl();
                if (TextUtils.isEmpty(landingPageUrl)) {
                    Log.i(TAG, "获取分享链接失败，landingPageUrl == null");
                    return;
                }
                Log.i(TAG, "获取分享链接成功，landingPageUrl == " + landingPageUrl);
                OdinSpUtil.setString(MainActivity.this, Constant.SHARE_URL, landingPageUrl);
                GlobalUtil.setShareUrl(landingPageUrl);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
