package com.odin.install.demo.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.Constant;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.R;
import com.odin.install.demo.ui.dialog.UserNameDialog;
import com.odin.install.demo.utils.OdinSpUtil;
import com.odin.install.demo.utils.QRCodeUtil;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.data.ShareData;
import com.odin.odininstall.listener.AppShareAdapter;

/**
 * 用户溯源
 */
public class UserTraceActivity extends BaseSchemeActivity implements UserNameDialog.OnEnsureClickListener {

    private static final String TAG = "UserTraceActivity";
    private TextView mTvUserId;
    private ImageView mImgQRCode;
    private UserNameDialog userNameDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_trace;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_user);
    }

    @Override
    public void initView() {
        super.initView();

        mTvUserId = findViewById(R.id.tv_user_trace_name);
        mImgQRCode = findViewById(R.id.img_user_trace_qr_code);
        findViewById(R.id.btn_user_relation).setOnClickListener(v -> startToActivity(UserRelationActivity.class));

        String userId = GlobalUtil.getUserId();
        if (TextUtils.isEmpty(userId)) {
            if (userNameDialog == null) {
                userNameDialog = new UserNameDialog(this);
                userNameDialog.setOnEnsureClickListener(this);
            }
            userNameDialog.show();
        } else {
            mTvUserId.setText(userId);
        }
        initQRCode(GlobalUtil.getShareUrl());
    }

    /**
     * 将分享的链接转为二维码
     *
     * @param shareUrl 分享的链接
     */
    private void initQRCode(String shareUrl) {
        if (!TextUtils.isEmpty(shareUrl)) {
            Bitmap qrCode = QRCodeUtil.createQRCodeBitmap(shareUrl, 400, 400,
                    "UTF-8", "H", "1", Color.BLACK, Color.WHITE);
            mImgQRCode.setImageBitmap(qrCode);
        } else {
            initOdinInstallShareUrl();
        }
    }

    /**
     * 获取install分享的URL地址
     */
    private void initOdinInstallShareUrl() {
        OdinInstall.getShareUrl(GlobalUtil.getUserId(), "test", new AppShareAdapter() {
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
                initQRCode(landingPageUrl);
                OdinSpUtil.setString(UserTraceActivity.this, Constant.SHARE_URL, landingPageUrl);
                GlobalUtil.setShareUrl(landingPageUrl);
            }
        });
    }

    @Override
    public void onEnsureClick(String userId) {
        if (mTvUserId != null) {
            mTvUserId.setText(userId);
            initOdinInstallShareUrl();
        }
    }
}
