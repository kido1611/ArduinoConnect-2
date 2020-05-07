package id.kido1611.arduinoconnect.modules.bluetooth;

import android.bluetooth.BluetoothDevice;

public class BluetoothItem {
    private BluetoothDevice device;
    private boolean paired;
    private boolean category;
    private String name;

    public BluetoothItem() {
        this.paired = false;
        this.category = false;
        this.device = null;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public boolean isPaired() {
        return paired;
    }

    public void setPaired(boolean paired) {
        this.paired = paired;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
