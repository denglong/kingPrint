package com.family_doctor.test_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.family_doctor.test_demo.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class DiviceListActivity extends BaseActivity {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_divice)
    TextView tv_divice;

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

        getAreaListAction();
    }

    @OnClick({R.id.tv_area, R.id.tv_divice})
    public void clickListAction(TextView tv) {
        switch (tv.getId()) {
            case R.id.tv_area:
                break;
            case R.id.tv_divice:
                startActivityForResult(new Intent(DiviceListActivity.this, BluetoothList.class), 3);
                break;
        }
    }

    private void getAreaListAction() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("userCode", "111111")
                .build();
        Request request = new Request.Builder()
                .url("http://39.104.204.55:8888/paid/appPrint/getPrinterList")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("error",response.body().string());
            }
        });



//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("+++++++++++", e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("11111---------", response.body().string());
//            }
//        });
    }
}
