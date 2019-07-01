package com.family_doctor.test_demo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.family_doctor.test_demo.R;
import com.family_doctor.test_demo.adapter.PrintAdapter;
import com.family_doctor.test_demo.model.PrintModel;
import com.family_doctor.test_demo.view.MyNoScrollListView;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

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

        PrintModel printModel = new PrintModel();
        printModel.setPrintNumber("1000");
        printModel.setPrice("55.00");
        printModel.setOrigin("湖南");
        printModel.setName("白沙（硬和气生财）");
        printModel.setSpecification("84mm");
        printModel.setBarcode("6901028193913");
        printModel.setChargeUnit("元/盒");
        printModel.setCigaretteId("070f533d024e4c939a0eef6497009bc2");
        for(int i=0; i<10; i++) {
            list.add(printModel);
        }
        PrintAdapter printAdapter = new PrintAdapter(this, list);
        print_list.setAdapter(printAdapter);

        print_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("PrintListActivity", String.format("%d", position));
            }
        });
    }
}
