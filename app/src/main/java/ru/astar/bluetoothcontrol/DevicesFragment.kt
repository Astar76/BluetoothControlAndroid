package ru.astar.bluetoothcontrol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.astar.bluetoothcontrol.databinding.FragmentDevicesBinding

class DevicesFragment : Fragment(), DevicesAdapter.Callback {

    private var _binding: FragmentDevicesBinding? = null
    private val binding: FragmentDevicesBinding get() = _binding!!

    private val devicesAdapter = DevicesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.devicesRecycler.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = devicesAdapter
        }
        
        devicesAdapter.addCallback(this)

        devicesAdapter.update(listOf(
            BluetoothDevice("Device 1", "00:00:00:00:00:01"),
            BluetoothDevice("Device 2", "00:00:00:00:00:02"),
            BluetoothDevice("Device 3", "00:00:00:00:00:03"),
            BluetoothDevice("Device 4", "00:00:00:00:00:04"),
            BluetoothDevice("Device 5", "00:00:00:00:00:05")
        ))
    }

    override fun onItemClick(device: BluetoothDevice) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.containerFragment, ControlFragment.newInstance(device.address))
            .commit()
    }

    companion object {
        fun newInstance() = DevicesFragment()
    }
}