package com.odin.install.demo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;

import com.odin.install.demo.Constant;
import com.odin.install.demo.R;

import cn.odinshare.odinonekeyshare.OneKeyShare;

public class GlobalUtil {

    private static String odinKey;
    private static String userId;
    private static String userIdParent;
    private static String shareUrl;
    private static String channelCode;

    private static Context context;

    public static void initialize(Context c) {
        context = c;
        initAppKey();
        initSp();
    }

    private static void initSp() {
        userId = OdinSpUtil.getString(context, Constant.USER_ID);
        userIdParent = OdinSpUtil.getString(context, Constant.USER_ID_PARENT);
        channelCode = OdinSpUtil.getString(context, Constant.CHANNEL_CODE);
    }

    private static void initAppKey() {
        Bundle bundle = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            bundle = packageInfo.applicationInfo.metaData;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (bundle != null) {
            odinKey = bundle.getString("Odin-AppKey");
        }
    }

    public static String getOdinKey() {
        return odinKey;
    }

    public static void setUserId(String userId) {
        GlobalUtil.userId = userId;
    }

    public static String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            initSp();
        }
        return userId;
    }

    public static void setUserIdParent(String userIdParent) {
        GlobalUtil.userIdParent = userIdParent;
    }

    public static String getUserIdParent() {
        return userIdParent;
    }

    public static String getShareUrl() {
        if (TextUtils.isEmpty(shareUrl)) {
            initSp();
        }
        return shareUrl;
    }

    public static void setShareUrl(String shareUrl) {
        GlobalUtil.shareUrl = shareUrl;
    }

    public static String getChannelCode() {
        if (TextUtils.isEmpty(channelCode)) {
            initSp();
        }
        return channelCode;
    }

    public static void oneKeyShare() {
        String url = "http://www.odinanalysis.com/.well-known/demo/install/index.html?odinkey=" + GlobalUtil.getOdinKey();
        oneKeyShare(url);
    }

    public static void oneKeyShare(String shareUrl) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        new OneKeyShare().setTitle(context.getString(R.string.app_name))
                .setText(context.getString(R.string.app_explain))
                .setUrl(shareUrl)
                .setImageData(bitmap)
                .show(context);
    }
}
