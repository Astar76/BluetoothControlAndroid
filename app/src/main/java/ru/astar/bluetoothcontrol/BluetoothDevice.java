package ru.astar.bluetoothcontrol;

public class BluetoothDevice {

    private final String name;
    private final String address;

    public BluetoothDevice(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
