package ru.astar.bluetoothcontrol

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.astar.bluetoothcontrol.databinding.ItemDeviceBinding

class DevicesAdapter : RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>() {

    private val items = mutableListOf<BluetoothDevice>()
    private var callback: Callback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<BluetoothDevice>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addCallback(callback: Callback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = ItemDeviceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class DeviceViewHolder(private val binding: ItemDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BluetoothDevice) {

            itemView.setOnClickListener { callback?.onItemClick(item) }

            binding.apply {
                textName.text = item.name
                textAddress.text = item.address
            }
        }
    }

    interface Callback {
        fun onItemClick(device: BluetoothDevice)
    }
}
