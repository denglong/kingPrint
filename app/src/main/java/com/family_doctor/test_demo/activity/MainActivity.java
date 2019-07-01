package com.family_doctor.test_demo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.family_doctor.test_demo.R;
import print.Print;

public class MainActivity extends BaseActivity {
                                                             
    @BindView(R.id.tv_bottom_01)
    TextView homeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_bottom_01, R.id.tv_bottom_02, R.id.tv_bottom_03, R.id.tv_bottom_04})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_bottom_01:
                String mac = "Bluetooth," + "8C:DE:52:BC:8D:B3";
                try {
                    int type = Print.PortOpen(this, mac);
                    if (type == 0) {
                        byte characterFont = 1;
                        Print.SelectCharacterFont(characterFont);
                        Print.LanguageEncode="gb2312";
                        Log.e("--------------", "连接成功");
                    }
                    else {
                        Log.e("--------------", "连接失败");
                    }
                }
                catch (Exception e) {

                }
                break;
            case R.id.tv_bottom_02:
                try {
//                    Print.PrintText("111", 0, 01, 10);
//                    Print.PrintText("111", 0, 01, 11);
//                    Print.PrintText("111", 0, 01, 20);
//                    Print.PrintText("111", 0, 01, 30);
//                    Print.PrintText("111", 0, 01, 40);
//                    Print.PrintText("111", 0, 01, 50);
//                    Print.PrintText("111", 0, 01, 60);
//                    Print.PrintText("111", 0, 01, 70);
//                    Print.PrintText("111", 0, 01, 80);
//                    Print.PrintText("111", 0, 01, 90);
//
//                    Print.PrintText("参评", 1, 01, 00);
//                    Print.PrintText("参评", 1, 01, 01);
//                    Print.PrintText("参评", 1, 01, 02);
//                    Print.PrintText("参评", 1, 01, 03);
//                    Print.PrintText("参评", 1, 01, 04);
//                    Print.PrintText("参评", 1, 01, 05);
//                    Print.PrintText("参评", 1, 01, 06);
//                    Print.PrintText("参评", 1, 01, 07);

                    Print.PrintAndFeed(100);

//                    Print.PrintText("品名",0,01,00);
//                    Print.PrintText("产地",2,01,00);
//                    Print.PrintText("规格",0,01,00);
//                    Print.PrintText("计价单位",2,01,00);
//                    Print.PrintAndReturnStandardMode();
//                    int type = Print.PrintAndFeed(500);
                }
                catch (Exception e){

                }
                break;
            case R.id.tv_bottom_03:
                try {
                    Print.SelectPageMode();
                    Print.SetPageModePrintArea(115,0,570,190);
                    Print.SetPageModePrintDirection(0);
                    Print.SetPageModeAbsolutePosition(360,65);
                    Print.PrintText("99",0,01,33);
                    Print.SetPageModeAbsolutePosition(170,125);
                    Print.PrintText("品名",0,01,00);
                    Print.SetPageModeAbsolutePosition(380,125);
                    Print.PrintText("产地",0,01,00);
                    Print.SetPageModeAbsolutePosition(170,150);
                    Print.PrintText("规格",0,01,00);
                    Print.SetPageModeAbsolutePosition(380,150);
                    Print.PrintText("计价单位",0,01,00);

                    Print.PrintDataInPageMode();
                    Print.ClearPageModePrintAreaData();
//                    Print.GotoNextLabel();
                }
                catch (Exception e) {

                }
                break;
            case R.id.tv_bottom_04:
                try {
                    Print.SelectPageMode();
                    Print.SetPageModePrintArea(0,0,570,190);
                    Print.SetPageModePrintDirection(0);
                    Print.SetPageModeAbsolutePosition(280,80);
                    Print.PrintText("33.00",0,01,33);
                    Print.SetPageModeAbsolutePosition(65,135);
                    Print.PrintText("品名",0,01,00);
                    Print.SetPageModeAbsolutePosition(280,135);
                    Print.PrintText("产地",0,01,00);
                    Print.SetPageModeAbsolutePosition(65,160);
                    Print.PrintText("规格",0,01,00);
                    Print.SetPageModeAbsolutePosition(280,160);
                    Print.PrintText("计价单位",0,01,00);

                    Print.PrintDataInPageMode();
                    Print.ClearPageModePrintAreaData();
                    Print.GotoNextLabel();
                }
                catch (Exception e) {

                }
                break;
        }
    }
}
