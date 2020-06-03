package com.odin.install.demo.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.odin.install.demo.Constant;
import com.odin.install.demo.R;
import com.odin.install.demo.utils.OdinSpUtil;
import com.odin.odininstall.OdinInstall;
import com.odin.odininstall.listener.AppRegisterListener;
import com.odin.odininstall.model.Error;

/**
 * 注册用户的Dialog
 */
public class UserNameDialog extends Dialog {

    private static final String TAG = "UserNameDialog";
    private EditText mEtUserName;
    private OnEnsureClickListener onEnsureClickListener;

    public UserNameDialog(@NonNull Context context) {
        super(context);
    }

    public void setOnEnsureClickListener(OnEnsureClickListener onEnsureClickListener) {
        this.onEnsureClickListener = onEnsureClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_user_name);

        setCanceledOnTouchOutside(false);

        mEtUserName = findViewById(R.id.et_user_name);
        Button mBtnCancel = findViewById(R.id.btn_cancel);
        Button mBtnEnsure = findViewById(R.id.btn_ensure);
        mBtnCancel.setOnClickListener(v -> dismiss());
        mBtnEnsure.setOnClickListener(v -> {
            final String userId = mEtUserName.getText().toString().trim();
            if (TextUtils.isEmpty(userId)) {
                Toast.makeText(getContext(), "用户Id不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            OdinInstall.reportRegister(userId, "123456@qq.com", "13412345678", new AppRegisterListener() {

                @Override
                public void onRegisterRecive(String parentUserID, Error error) {
                    Log.i(TAG, "注册完成，上级用户Id: " + parentUserID + "，error: " + error);
                    if (error == null) {
                        if (!TextUtils.isEmpty(parentUserID)) {
                            OdinSpUtil.setString(getContext(), Constant.USER_ID_PARENT, parentUserID);
                        }
                        OdinSpUtil.setString(getContext(), Constant.USER_ID, userId);
                    } else {
                        Toast.makeText(getContext(), "注册失败：" + error.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if (onEnsureClickListener != null) {
                onEnsureClickListener.onEnsureClick(userId);
            }
            dismiss();
        });
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public interface OnEnsureClickListener {
        void onEnsureClick(String userId);
    }
}
