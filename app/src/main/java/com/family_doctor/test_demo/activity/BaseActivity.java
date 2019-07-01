package com.family_doctor.test_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.family_doctor.test_demo.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //参数一：Activity2进入动画  参数二：Activity1退出动画
        overridePendingTransition(R.anim.activity_slide_enter_right, R.anim.activity_alpha_exit);
    }

    @Override
    public void startActivityForResult(Intent intent, int number) {
        super.startActivityForResult(intent, number);
        overridePendingTransition(R.anim.activity_slide_enter_right, R.anim.activity_alpha_exit);
    }

    @Override
    public void finish() {
        super.finish();
        //参数一：Activity1进入动画，参数二：Activity2退出动画
        overridePendingTransition(R.anim.activity_alpha_enter, R.anim.activity_slide_exit_right);
    }

    public void showToast(String text, boolean type) {
        if (type) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
