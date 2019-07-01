package com.family_doctor.test_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.family_doctor.test_demo.R;
import com.family_doctor.test_demo.model.BlueDivice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: BluetoothItemAdapter
 * Author: wangp
 * Comment: 周围蓝牙设备列表
 * Date: 2018-08-23 12:16
 */
public class BluetoothItemAdapter2 extends BaseAdapter {
    private Context          mContext;
    private List<BlueDivice> mList;
    public static int CONNECTED = 3;
    private String[] mStateArray = {"未绑定", "绑定中", "已绑定", "已连接"};
    public BluetoothItemAdapter2(Context context, List<BlueDivice> list) {
        this.mContext = context;
        this.mList = list;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetooth_device, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        BlueDivice bluetoothDevice = mList.get(position);
        viewHolder.tvName.setText("名称\n" + bluetoothDevice.name + "");
        viewHolder.tvAddress.setText("地址\n" + bluetoothDevice.address + "");
        viewHolder.tvState.setText("状态\n" + mStateArray[bluetoothDevice.state] + "");
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_state)
        TextView tvState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
