package ru.astar.bluetoothcontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import ru.astar.bluetoothcontrol.databinding.FragmentControlBinding

class ControlFragment : Fragment() {

    private var _binding: FragmentControlBinding? = null
    private val binding: FragmentControlBinding get() = _binding!!

    private val viewModel: ControlViewModel by viewModels {
        ControlViewModelFactory((requireActivity().application as App).adapterProvider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ledFirst.setOnCheckedChangeListener { _, checked ->
            viewModel.enableLed(Led.FIRST, checked)
        }

        binding.ledSecond.setOnCheckedChangeListener { _, checked ->
            viewModel.enableLed(Led.SECOND, checked)
        }

        binding.angleServo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, p1: Int, p2: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                viewModel.setAngleServo(seekBar.progress)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.disconnect()
    }

    override fun onResume() {
        super.onResume()

        val deviceAddress = requireArguments().getString(KEY_DEVICE_ADDRESS)!!
        viewModel.connect(deviceAddress)
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