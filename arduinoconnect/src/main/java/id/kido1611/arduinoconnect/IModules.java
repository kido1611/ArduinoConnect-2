package id.kido1611.arduinoconnect;

public interface IModules {
    public void showDialog();
    public void connect();
    public void disconnect();
    public void sendData(byte data);
    public void sendData(byte data[]);
    public byte[] readData();
}
