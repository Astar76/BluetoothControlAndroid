package ru.astar.bluetoothcontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.astar.bluetoothcontrol.databinding.FragmentControlBinding

class ControlFragment : Fragment() {


    private var _binding: FragmentControlBinding? = null
    private val binding: FragmentControlBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        private const val KEY_DEVICE_ADDRESS = "key_device_address"
        @JvmStatic
        fun newInstance(deviceAddress: String) = ControlFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_DEVICE_ADDRESS, deviceAddress)
            }
        }
    }
}