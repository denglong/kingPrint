package com.family_doctor.test_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TabHost;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.family_doctor.test_demo.R;
import com.xuexiang.xui.widget.edittext.ClearEditText;

public class LoginActivity extends BaseActivity {

    static final String TAG = "LoginActivigy";
    @BindView(R.id.account_text)
    ClearEditText account;
    @BindView(R.id.pwd_text)
    ClearEditText pwd;

    @Override
    protected void onCreate(Bundle saveIntent) {
        super.onCreate(saveIntent);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_confirm)
    public void clickConfirmLogin() {
        Log.e(TAG, account.getText().toString()+"--------"+pwd.getText().toString());
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            finish();
            overridePendingTransition(R.anim.activity_slide_enter_bottom, R.anim.activity_slide_exit_bottom);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
