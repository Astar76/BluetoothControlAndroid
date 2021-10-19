package ru.astar.bluetoothcontrol

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigate(DevicesFragment.newInstance())

        enableBluetooth()
    }

    private fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .commit()
    }

    private fun enableBluetooth() {
        requestEnableBluetooth.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
    }

    private val requestEnableBluetooth = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_CANCELED) {
            showEnableBluetoothMessage()
        }
    }

    private fun showEnableBluetoothMessage() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.enable_bluetooth))
            .setMessage(getString(R.string.enable_bluetooth_message))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                enableBluetooth()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                finish()
            }
            .create()
            .show()
    }
}