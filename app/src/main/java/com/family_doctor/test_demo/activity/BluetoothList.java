package com.family_doctor.test_demo.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.family_doctor.test_demo.R;
import com.family_doctor.test_demo.adapter.BluetoothItemAdapter;
import com.family_doctor.test_demo.adapter.BluetoothItemAdapter2;
import com.family_doctor.test_demo.model.BlueDivice;
import com.family_doctor.test_demo.view.MyNoScrollListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import print.Print;

public class BluetoothList extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.top_navigation)
    TitleBar titleBar;
    @BindView(R.id.btn_sousuo)
    Button btnSousuo;
    @BindView(R.id.lv_bluetooth)
    MyNoScrollListView lvBluetooth;
    private final String TAG = "BluetoothList";
    @BindView(R.id.lv_bluetooth2)
    MyNoScrollListView lvBluetooth2;
    private BluetoothAdapter mBluetooth;
    private List<BlueDivice> mDeviceList  = new ArrayList<BlueDivice>();
    private List<BlueDivice> mDeviceList2 = new ArrayList<BlueDivice>();
    private BluetoothItemAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);
        ButterKnife.bind(this);
        initData();
        initView();

        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        //已绑定蓝牙列表
        Set<BluetoothDevice> bdevices = mBluetooth.getBondedDevices();
        if (bdevices.size() > 0) {
            for (BluetoothDevice device : bdevices) {
                //保存到arrayList集合中
                BlueDivice item = new BlueDivice(device.getName(), device.getAddress(), device.getBondState() - 10);
                mDeviceList.add(item);
                BluetoothItemAdapter adapter = new BluetoothItemAdapter(BluetoothList.this, mDeviceList);
                lvBluetooth.setAdapter(adapter);
            }
        }
    }

    private void initData() {
        //蓝牙打开状态
        mBluetooth = BluetoothAdapter.getDefaultAdapter();
        //cbOpen.setChecked(mBluetooth.isEnabled());
        if (mBluetooth == null) {
            Toast.makeText(this, "本机未找到蓝牙功能", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!mBluetooth.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);//可被搜索120s
            startActivityForResult(intent, 1);
        }
        beginDiscovery();
        lvBluetooth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cancelDiscovery();
                BlueDivice item = mDeviceList.get(position);
                BluetoothDevice device = mBluetooth.getRemoteDevice(item.address);
                try {
                    if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                        Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                        Log.e(TAG, "开始配对");
                        Boolean result = (Boolean) createBondMethod.invoke(device);
                    } else if (device.getBondState() == BluetoothDevice.BOND_BONDED &&
                            item.state != BluetoothItemAdapter.CONNECTED) {
                        //  btnSousuo.setText("开始连接");
                        if (connectBluetoothDivice(item.address)) {
                            Intent intent = new Intent(BluetoothList.this, HomeActivity.class);
                            intent.putExtra("address", item.address);
                            setResult(3, intent);
                            finish();
                        }
                    } else if (device.getBondState() == BluetoothDevice.BOND_BONDED &&
                            item.state == BluetoothItemAdapter.CONNECTED) {
                        //btnSousuo.setText("正在发送消息");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    btnSousuo.setText("配对异常：" + e.getMessage());
                }
            }
        });

        lvBluetooth2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cancelDiscovery();
                BlueDivice item = mDeviceList2.get(position);
                BluetoothDevice device = mBluetooth.getRemoteDevice(item.address);
                try {
                    if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                        Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                        Log.e(TAG, "开始配对");
                        Boolean result = (Boolean) createBondMethod.invoke(device);
                    } else if (device.getBondState() == BluetoothDevice.BOND_BONDED &&
                            item.state != BluetoothItemAdapter2.CONNECTED) {
                        btnSousuo.setText("开始连接");
                        if (connectBluetoothDivice(item.address)) {
                            Intent intent = new Intent(BluetoothList.this, HomeActivity.class);
                            intent.putExtra("address", item.address);
                            setResult(3, intent);
                            finish();
                        }
                    } else if (device.getBondState() == BluetoothDevice.BOND_BONDED &&
                            item.state == BluetoothItemAdapter2.CONNECTED) {
                        btnSousuo.setText("正在发送消息");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    btnSousuo.setText("配对异常：" + e.getMessage());
                }
            }
        });

    }


    @OnClick({R.id.btn_sousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sousuo:
                beginDiscovery();
                break;
        }
    }

    private void beginDiscovery() {
        if (mBluetooth.isDiscovering() != true) {
            mDeviceList2.clear();
            adapter2 = new BluetoothItemAdapter2(BluetoothList.this, mDeviceList2);
            lvBluetooth2.setAdapter(adapter2);
            btnSousuo.setText("正在搜索蓝牙设备");
            mBluetooth.startDiscovery();
        }
    }


    private void cancelDiscovery() {
        btnSousuo.setText("取消搜索蓝牙设备");
        if (mBluetooth.isDiscovering()) {
            mBluetooth.cancelDiscovery();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //所有可用蓝牙列表
        blueReceiver = new BluetoothReceiver();
        //需要过滤多个动作，则调用IntentFilter对象的addAction添加新动作
        IntentFilter foundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        foundFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        foundFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(blueReceiver, foundFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        cancelDiscovery();
        unregisterReceiver(blueReceiver);
    }

    private BluetoothReceiver blueReceiver;

    private class BluetoothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e(TAG, "onReceive action=" + action);
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.e(TAG, "ACTION_FOUND名称===" + device.getName() + "地址===" + device.getAddress());
                if (device.getName() != null && device.getAddress() != null) {
                    BlueDivice item = new BlueDivice(device.getName(), device.getAddress(), device.getBondState() - 10);
                    mDeviceList2.add(item);
                }
                Log.e(TAG, "ACTION_FOUND===" + mDeviceList2.toString());
                if (adapter2 != null) {
                    adapter2.notifyDataSetChanged();
                }
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                btnSousuo.setText("蓝牙设备搜索完成");
                if (adapter2 != null) {
                    adapter2.notifyDataSetChanged();
                }
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() == BluetoothDevice.BOND_BONDING) {
                    btnSousuo.setText("正在配对" + device.getName());
                } else if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    btnSousuo.setText("完成配对" + device.getName());

                } else if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                    btnSousuo.setText("取消配对" + device.getName());
                }

            } else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {

            }
        }
    }

    //连接设备
    private boolean connectBluetoothDivice(String mac) {
        mac = "Bluetooth," + mac;
        try {
            int type = Print.PortOpen(this, mac);
            if (type == 0) {
                byte characterFont = 0;
                Print.SelectCharacterFont(characterFont);
                Print.LanguageEncode="gb2312";
                Log.e("--------------", "连接成功");
                return true;
            }
            else {
                Log.e("--------------", "连接失败");
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
