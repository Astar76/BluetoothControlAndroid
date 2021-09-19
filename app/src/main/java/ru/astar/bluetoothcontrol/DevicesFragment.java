package ru.astar.bluetoothcontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.astar.bluetoothcontrol.databinding.FragmentDevicesBinding;

public class DevicesFragment extends Fragment implements DevicesAdapter.Callback {

    private FragmentDevicesBinding binding;
    private final DevicesAdapter devicesAdapter = new DevicesAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDevicesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        binding.devicesRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.devicesRecycler.setAdapter(devicesAdapter);
        binding.devicesRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL));

        devicesAdapter.setCallback(this);

        List<BluetoothDevice> items = new ArrayList<>();
        items.add(new BluetoothDevice("Device 1", "00:00:00:00:00:01"));
        items.add(new BluetoothDevice("Device 2", "00:00:00:00:00:02"));
        items.add(new BluetoothDevice("Device 3", "00:00:00:00:00:03"));
        items.add(new BluetoothDevice("Device 4", "00:00:00:00:00:04"));
        items.add(new BluetoothDevice("Device 5", "00:00:00:00:00:05"));
        devicesAdapter.update(items);
    }

    @Override
    public void onItemClick(BluetoothDevice device) {
        navigateToControlScreen(device.getAddress());
    }

    private void navigateToControlScreen(String address) {
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.containerFragment, ControlFragment.newInstance(address))
                .commit();
    }

    public static DevicesFragment newInstance() {
        return new DevicesFragment();
    }
}
