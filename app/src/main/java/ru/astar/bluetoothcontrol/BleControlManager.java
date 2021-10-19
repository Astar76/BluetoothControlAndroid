package ru.astar.bluetoothcontrol;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.UUID;

import no.nordicsemi.android.ble.BleManager;

public class BleControlManager extends BleManager {

    public static final UUID SERVICE_CONTROL_UUID = UUID.fromString("6f59f19e-2f39-49de-8525-5d2045f4d999");
    public static final UUID SERVICE_WORK_TIME_UUID = UUID.fromString("f790145d-61dd-4464-9414-c058448ee9f2");
    public static final UUID CONTROL_REQUEST_UUID = UUID.fromString("420ece2e-c66c-4059-9ceb-5fc19251e453");
    public static final UUID CONTROL_RESPONSE_UUID = UUID.fromString("a9bf2905-ee69-4baa-8960-4358a9e3a558");
    public static final UUID WORK_TIME_UUID = UUID.fromString("5e9f22d4-a305-4113-8fa2-55c5d497c3f2");

    public static final int CMD_CONTROL_LED = 0x1;
    public static final int CMD_CONTROL_SERVO = 0x2;

    public static final int LED_FIRST = 25;


    private BluetoothGattCharacteristic controlRequest;
    private BluetoothGattCharacteristic controlResponse;
    private BluetoothGattCharacteristic workTime;

    public BleControlManager(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected BleManagerGattCallback getGattCallback() {
        return new BleControlManagerGattCallback();
    }

    public void enableLed(Led led, boolean enable) {
        writeCharacteristic(
                controlRequest,
                new byte[]{CMD_CONTROL_LED, led.getPin(), (byte) ((enable) ? 0x1 : 0x0)},
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        ).enqueue();
    }

    public void setAngleServo(int angle) {
        writeCharacteristic(
                controlRequest,
                new byte[]{CMD_CONTROL_SERVO, (byte) angle},
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        ).enqueue();
    }

    class BleControlManagerGattCallback extends BleManagerGattCallback {

        @Override
        protected boolean isRequiredServiceSupported(@NonNull BluetoothGatt gatt) {
            BluetoothGattService controlService = gatt.getService(SERVICE_CONTROL_UUID);
            BluetoothGattService workTimeService = gatt.getService(SERVICE_WORK_TIME_UUID);

            if (controlService != null && workTimeService != null) {
                controlRequest = controlService.getCharacteristic(CONTROL_REQUEST_UUID);
                controlResponse = controlService.getCharacteristic(CONTROL_RESPONSE_UUID);
                workTime = workTimeService.getCharacteristic(WORK_TIME_UUID);
            }

            return controlRequest != null && controlResponse != null && workTime != null;
        }

        @Override
        protected void onServicesInvalidated() {
            controlRequest = null;
            controlResponse = null;
            workTime = null;
        }
    }
}
