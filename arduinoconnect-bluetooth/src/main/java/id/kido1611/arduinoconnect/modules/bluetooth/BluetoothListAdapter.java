package id.kido1611.arduinoconnect.modules.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BluetoothListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BluetoothItem> items;

    public BluetoothListAdapter(Context context, ArrayList<BluetoothItem> items) {
        this.context = context;
        this.items = items;
    }

    public boolean isAvailable(BluetoothDevice device){
        if(device==null){
            return true;
        }
        BluetoothDevice currDevice;
        boolean found = false;
        int i=0;
        while(i<items.size() && !found){
            currDevice = items.get(i).getDevice();
            if(currDevice!=null) {
                if (currDevice.getAddress().equals(device.getAddress()))
                    found = true;
            }
            i++;
        }
        return found;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BluetoothItem item = items.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.bluetooth_list_item, parent, false);
            viewHolder.title = convertView.findViewById(R.id.tvTitle);
            viewHolder.macAddress = convertView.findViewById(R.id.tvMac);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BluetoothDevice device = item.getDevice();

        viewHolder.title.setText(device.getName());
        viewHolder.macAddress.setText(device.getAddress());

        if(item.isPaired()){
            viewHolder.title.setText(device.getName()+"(Paired)");
        }

        return convertView;
    }

    static class ViewHolder{
        TextView title, macAddress;
    }
}
