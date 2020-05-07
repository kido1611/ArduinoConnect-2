package id.kido1611.arduinoconnect.modules.bluetooth;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Set;

import id.kido1611.arduinoconnect.IModules;

public class BluetoothModules extends DialogFragment implements IModules {

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothListAdapter bluetoothListAdapter;

    private ArrayList<BluetoothItem> items = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppCompatAlertDialog);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothListAdapter = new BluetoothListAdapter(getContext(), items);

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

        // TODO unregister receiver
        getActivity().registerReceiver(mReceiver, filter);

        refreshList();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void sendData(byte data) {

    }

    @Override
    public void sendData(byte[] data) {

    }

    @Override
    public byte[] readData() {
        return new byte[0];
    }

    private void refreshList(){
        items.clear();

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            for(BluetoothDevice device: pairedDevices){
                BluetoothItem item = new BluetoothItem();
                item.setDevice(device);
                item.setPaired(true);
                items.add(item);
            }
        }

        bluetoothListAdapter.notifyDataSetChanged();
        bluetoothAdapter.cancelDiscovery();
        if(!bluetoothAdapter.isDiscovering()){
            // TODO Cancel discover
            bluetoothAdapter.startDiscovery();
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {

            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mProgressView.setVisibility(View.VISIBLE);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                mProgressView.setVisibility(View.GONE);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device==null) return;
                if(!bluetoothListAdapter.isAvailable(device)){
                    BluetoothItem item = new BluetoothItem();
                    item.setDevice(device);
                    item.setPaired(false);
                    item.setCategory(false);
                    items.add(item);
                    bluetoothListAdapter.notifyDataSetChanged();
                }
            }else if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){
                mProgressDialog.dismiss();
                refreshList();
            }
        }
    };
}
