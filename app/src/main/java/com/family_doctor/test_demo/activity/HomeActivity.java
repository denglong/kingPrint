package com.family_doctor.test_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.family_doctor.test_demo.R;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.xuexiang.xui.widget.XUIWrapContentListView;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;

    @BindView(R.id.tv_listView)
    XUIWrapContentListView listView;

    @Override
    protected void onCreate(Bundle saveIntent) {
        super.onCreate(saveIntent);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_bottom_01, R.id.tv_bottom_02})
    public void clickAction(Button button) {
        switch (button.getId()) {
            case R.id.tv_bottom_01:
                startActivity(new Intent(HomeActivity.this, DiviceListActivity.class));
                break;
            case R.id.tv_bottom_02:
                startActivity(new Intent(HomeActivity.this, PrintListActivity.class));
                break;
        }
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
