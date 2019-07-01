package com.family_doctor.test_demo;

import android.app.Application;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.xuexiang.xui.XUI;

public class StartActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        QMUISwipeBackActivityManager.init(this);
        XUI.init(this);
    }
}
