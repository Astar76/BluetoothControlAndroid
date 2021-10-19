package ru.astar.bluetoothcontrol

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context

interface BluetoothAdapterProvider {

    fun getAdapter(): BluetoothAdapter

    fun getContext(): Context

    class Base(private val context: Context) : BluetoothAdapterProvider {
        override fun getAdapter(): BluetoothAdapter {
            val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            return manager.adapter
        }

        override fun getContext(): Context {
            return context
        }
    }
}