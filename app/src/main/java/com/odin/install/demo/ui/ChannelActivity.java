package com.odin.install.demo.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.install.demo.BaseInstallActivity;
import com.odin.install.demo.Constant;
import com.odin.install.demo.R;
import com.odin.install.demo.data.ChannelData;
import com.odin.install.demo.data.ChannelRequest;
import com.odin.install.demo.network.HttpService;
import com.odin.install.demo.network.InstallApi;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.utils.OdinSpUtil;
import com.odin.install.demo.utils.QRCodeUtil;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.data.ShareData;
import com.odin.odininstall.listener.AppShareAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 渠道效果，查询渠道的统计数据
 */
public class ChannelActivity extends BaseInstallActivity {

    private TextView mTvVisits;
    private TextView mTvOnclick;
    private TextView mTvInstall;
    private TextView mTvCall;
    private ImageView mImgQRCode;
    private static final String TAG = "ChannelActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_channel;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_channel);
    }

    @Override
    public void initView() {
        super.initView();
        mTvCall = findViewById(R.id.tv_call_num);
        mTvOnclick = findViewById(R.id.tv_onclick_num);
        mTvInstall = findViewById(R.id.tv_install_num);
        mTvVisits = findViewById(R.id.tv_visits_num);
        mImgQRCode = findViewById(R.id.img_channel_qr_code);
        initQRCode(GlobalUtil.getShareUrl());
        queryChannelData();
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

    private void initChannelDataView(ChannelData.DataBean dataBean) {
        if (dataBean == null) {
            return;
        }
        int visitsNum = dataBean.getVisitsNum();
        int clickNum = dataBean.getClickNum();
        int installNum = dataBean.getInstallNum();
        int callOnNum = dataBean.getCallOnNum();
        mTvCall.setText(String.valueOf(callOnNum));
        mTvOnclick.setText(String.valueOf(clickNum));
        mTvInstall.setText(String.valueOf(installNum));
        mTvVisits.setText(String.valueOf(visitsNum));
    }

    private void queryChannelData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(new Date());

        ChannelRequest.PageBean pageBean = new ChannelRequest.PageBean(1, 10);
        ChannelRequest.ParamBean paramBean = new ChannelRequest.ParamBean(GlobalUtil.getOdinKey(),
                "custom", date + " 00:00:00", date + " 23:59:59");

        ChannelRequest channelRequest = new ChannelRequest(pageBean, paramBean);
        Disposable subscribe = HttpService.createRetrofit(InstallApi.class)
                .getChannelData(channelRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(channelData -> {
                    Log.i(TAG, "请求成功: " + channelData.toString());
                    if (channelData.getCode() == 0) {
                        List<ChannelData.DataBean> data = channelData.getData();
                        for (ChannelData.DataBean datum : data) {
                            if (datum.getChannelCode().equals(GlobalUtil.getChannelCode())) {
                                initChannelDataView(datum);
                            }
                        }
                    }
                }, throwable -> Log.e(TAG, "请求失败: " + throwable.getMessage()));
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
                OdinSpUtil.setString(ChannelActivity.this, Constant.SHARE_URL, landingPageUrl);
                GlobalUtil.setShareUrl(landingPageUrl);
            }
        });
    }
}
