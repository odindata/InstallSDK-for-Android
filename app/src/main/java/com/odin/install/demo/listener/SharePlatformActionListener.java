package com.odin.install.demo.listener;

import android.app.Activity;
import android.widget.Toast;

import java.util.HashMap;

import cn.odinshare.core.Platform;
import cn.odinshare.core.PlatformActionListener;

/**
 * 分享回调事件
 * <p>
 * 注意：回调事件是在子线程中运行的，如需要在主线程中使用，请使用Handler或其他方法使其运行在主线程中
 */
public class SharePlatformActionListener implements PlatformActionListener {

    private Activity activity;

    public SharePlatformActionListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onComplete(Platform platform, final int action, final HashMap<String, Object> res) {
        String text = "";
        if (action == Platform.ACTION_SHARE) {
            text = "分享成功";
        }
        final String completeText = text;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, completeText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        String text = "";
        if (action == Platform.ACTION_SHARE) {
            text = "分享失败：" + t.getMessage();
        }
        final String errorText = text;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCancel(Platform platform, int action) {
        String text = "";
        if (action == Platform.ACTION_SHARE) {
            text = "已取消分享";
        }
        final String cancelText = text;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, cancelText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
