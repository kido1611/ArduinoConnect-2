package id.kido1611.arduinoconnect;

public interface ArduinoConnectCallback {
    void onSerialTextReceived(String text);
    void onArduinoConnected();
    void onArduinoDisconnected();
    void onArduinoNotConnected();
    void onArduinoConnectFailed();
    void onBluetoothDeviceNotFound();
    void onBluetoothFailedEnabled();
}
