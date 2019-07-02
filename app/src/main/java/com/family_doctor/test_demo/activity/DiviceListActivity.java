package com.family_doctor.test_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.family_doctor.test_demo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.popupwindow.popup.XUIListPopup;
import com.xuexiang.xui.widget.popupwindow.popup.XUIPopup;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiviceListActivity extends BaseActivity {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_divice)
    TextView tv_divice;
    List<Map<String, Object>> list;
    XUIListPopup mListPopup;

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
        ArrayList<String> myList = new ArrayList<String>();
        for(int i=0; i<list.size(); i++) {
            Map<String, Object> map = list.get(i);
            myList.add(map.get("areaName").toString());
        }
        switch (tv.getId()) {
            case R.id.tv_area:
                new MaterialDialog.Builder(this)
                        .title("区域选择")
                        .items(myList)
                        .itemsCallbackSingleChoice(
                                0,
                                (dialog, view, which, text) -> {
                                    tv_area.setText(text);
                                    return true;
                                })
                        .positiveText("选择")
                        .negativeText("取消")
                        .show();
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
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("+++++++++++", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String jsonData = response.body().string();
                Map<String, Object> map = gson.fromJson(jsonData, new TypeToken<Map<String, Object>>(){}.getType());
                if (Integer.parseInt(map.get("statusCode").toString()) == 200) {

                    Map<String, List<Map<String, Object>>> data = (Map<String, List<Map<String, Object>>>) map.get("data");

                    list = (List<Map<String, Object>>)data.get("printerList");
                    Map<String, Object> dic = list.get(0);
                    Log.e("11111---------", dic.get("areaName").toString());
                }
            }
        });
    }
}
