package com.family_doctor.test_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.family_doctor.test_demo.R;
import com.family_doctor.test_demo.model.PrintModel;

import java.util.List;

public class PrintAdapter extends BaseAdapter {

    private Context mContext;
    private List<PrintModel> mList;

    public PrintAdapter(Context context, List<PrintModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_print_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PrintModel printModel = mList.get(position);
        viewHolder.tv_barcode.setText("条形码：" + printModel.getBarcode());
        viewHolder.tv_origin.setText("产地：" + printModel.getOrigin());
        viewHolder.tv_name.setText(printModel.getName());
        viewHolder.tv_price.setText(printModel.getPrice()+printModel.getChargeUnit());
        viewHolder.tv_specification.setText("规格：" + printModel.getSpecification());
        viewHolder.tv_printNumber.setText("打印次数：" + printModel.getPrintNumber());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_barcode)
        TextView tv_barcode;
        @BindView(R.id.tv_origin)
        TextView tv_origin;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_specification)
        TextView tv_specification;
        @BindView(R.id.tv_printNumber)
        TextView tv_printNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
