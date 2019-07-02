package com.family_doctor.test_demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.family_doctor.test_demo.R;
import com.family_doctor.test_demo.adapter.PrintAdapter;
import com.family_doctor.test_demo.model.PrintModel;
import com.family_doctor.test_demo.view.MyNoScrollListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintListActivity extends BaseActivity {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;
    @BindView(R.id.print_list)
    MyNoScrollListView print_list;
    private List<PrintModel> list = new ArrayList<PrintModel>();

    @Override
    protected void onCreate(Bundle saveIntent) {
        super.onCreate(saveIntent);
        setContentView(R.layout.activity_print_list);
        ButterKnife.bind(this);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPrintListAction();

    }

    private void getPrintListAction() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("userCode", "111111")
                .build();
        Request request = new Request.Builder()
                .url("http://39.104.204.55:8888/paid/appPrint/getCigaretteList")
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
                Map<String, Object> map = gson.fromJson(jsonData, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (Integer.parseInt(map.get("statusCode").toString()) == 200) {

                    Map<String, List<Map<String, Object>>> data = (Map<String, List<Map<String, Object>>>) map.get("data");

                    List<Map<String, Object>> myList = data.get("cigaretteList");
                    for (int i = 0; i < myList.size(); i++) {
                        Map<String, Object> dic = myList.get(i);
                        PrintModel printModel = new PrintModel();
                        printModel.setPrintNumber(dic.get("printNumber").toString());
                        printModel.setPrice(dic.get("price").toString());
                        printModel.setOrigin(dic.get("origin").toString());
                        printModel.setName(dic.get("name").toString());
                        printModel.setSpecification(dic.get("specification").toString());
                        printModel.setBarcode(dic.get("barcode").toString());
                        printModel.setChargeUnit(dic.get("chargeUnit").toString());
                        printModel.setCigaretteId(dic.get("cigaretteId").toString());
                        list.add(printModel);
                    }
                    Log.e("------------", list.toString());
                    updateUiAction();
                }
            }
        });
    }

    private void updateUiAction() {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                PrintAdapter printAdapter = new PrintAdapter(PrintListActivity.this, list);
                print_list.setAdapter(printAdapter);

                print_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("PrintListActivity", String.format("%d", position));
                    }
                });
            }
        });
    }
}
