package ru.astar.bluetoothcontrol;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.astar.bluetoothcontrol.databinding.ItemDeviceBinding;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder> {

    private final List<BluetoothDevice> items = new ArrayList<>();
    private  Callback callback = null;

    @SuppressLint("NotifyDataSetChanged")
    public void update(List<BluetoothDevice> items)  {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public DevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeviceBinding binding = ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new DevicesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DevicesViewHolder extends RecyclerView.ViewHolder {

        private final ItemDeviceBinding binding;

        public DevicesViewHolder(@NonNull ItemDeviceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BluetoothDevice item) {
            itemView.setOnClickListener(view -> callback.onItemClick(item));
            binding.textName.setText(item.getName());
            binding.textAddress.setText(item.getAddress());
        }
    }

    public interface Callback {
        void onItemClick(BluetoothDevice device);
    }
}
