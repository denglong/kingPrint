package com.family_doctor.test_demo.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.family_doctor.test_demo.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class DiviceListActivity extends BaseActivity {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle saveIntent) {
        super.onCreate(saveIntent);
        setContentView(R.layout.activity_divice_list);
        ButterKnife.bind(this);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
