package ru.astar.bluetoothcontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.astar.bluetoothcontrol.databinding.FragmentControlBinding;

public class ControlFragment extends BaseFragment<FragmentControlBinding> {

    private static final String KEY_DEVICE_ADDRESS = "key_device_address";

    @Override
    FragmentControlBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentControlBinding.inflate(inflater, container, false);
    }

    public static ControlFragment newInstance(String address) {
        Bundle args = new Bundle();
        args.putString(KEY_DEVICE_ADDRESS, address);
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
