package com.odin.install.demo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.odin.install.demo.utils.GlobalUtil;
import com.odin.odininstall.OdinInstall;

import cn.odinshare.core.OdinShareSDK;

public class OdinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess()) {
            OdinInstall.init(this);
            OdinShareSDK.init(this);
        }
        GlobalUtil.initialize(this);
    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }

}
