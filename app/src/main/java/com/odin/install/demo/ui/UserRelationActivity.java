package com.odin.install.demo.ui;

import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.install.demo.BaseSchemeActivity;
import com.odin.install.demo.Constant;
import com.odin.install.demo.utils.GlobalUtil;
import com.odin.install.demo.R;
import com.odin.install.demo.adapter.UserRelationAdapter;
import com.odin.install.demo.data.UserRelation;
import com.odin.install.demo.network.HttpService;
import com.odin.install.demo.network.InstallApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户关系
 */
public class UserRelationActivity extends BaseSchemeActivity {

    private UserRelationAdapter userRelationAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_relation;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_user_relation);
    }

    @Override
    public void initView() {
        super.initView();

        String userId = GlobalUtil.getUserId();
        String userIdParent = GlobalUtil.getUserIdParent();
        String odinKey = GlobalUtil.getOdinKey();

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_relation);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<UserRelation> userRelationList = new ArrayList<>();
        userRelationList.add(new UserRelation(Constant.TYPE_TITLE, "我的账号"));
        userRelationList.add(new UserRelation(Constant.TYPE_CONTENT_ME, userId == null ? "" : userId));
        userRelationList.add(new UserRelation(Constant.TYPE_TITLE, "父级关系"));
        userRelationList.add(new UserRelation(Constant.TYPE_CONTENT_FATHER, userIdParent == null ? "" : userIdParent));
        userRelationList.add(new UserRelation(Constant.TYPE_TITLE, "子级关系"));
        userRelationAdapter = new UserRelationAdapter(userRelationList);
        mRecyclerView.setAdapter(userRelationAdapter);

        requestUserRelation(odinKey, userId);
    }

    private void requestUserRelation(String odinKey, String userId) {
        if (TextUtils.isEmpty(odinKey) || TextUtils.isEmpty(userId)) {
            return;
        }
        Disposable disposable = HttpService.createRetrofit(InstallApi.class).getUserRelation(odinKey, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userData -> {
                    int code = userData.getCode();
                    if (code == 0) {
                        List<String> data = userData.getData();
                        List<UserRelation> userRelationList = new ArrayList<>();
                        for (String userId1 : data) {
                            userRelationList.add(new UserRelation(Constant.TYPE_CONTENT_CHILD, userId1));
                        }
                        userRelationAdapter.addUserRelation(userRelationList);
                    }
                }, throwable -> {

                });
    }
}
