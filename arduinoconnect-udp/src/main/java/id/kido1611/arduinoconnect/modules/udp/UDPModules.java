package id.kido1611.arduinoconnect.modules.udp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import id.kido1611.arduinoconnect.IModules;

public class UDPModules extends DialogFragment implements IModules {

    private MulticastSocket multicastSocket;
    private InetAddress inetAddress;
    private DatagramPacket datagramPacket;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.connect_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
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
}
