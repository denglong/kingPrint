package com.family_doctor.test_demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Description：类的功能描述
 * Copyright  ：Copyright（c）2016
 * Author     ：feng
 * Date       ：2016/7/20 14:58
 */
public class MyNoScrollListView extends ListView {
    public MyNoScrollListView(Context context) {
        //super(context);
        this(context, null);
    }

    public MyNoScrollListView(Context context, AttributeSet attrs) {
        //super(context, attrs);
        this(context, attrs, 0);
    }

    public MyNoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}

