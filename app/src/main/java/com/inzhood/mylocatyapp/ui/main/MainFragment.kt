package com.inzhood.mylocatyapp.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.inzhood.mylocatyapp.databinding.FragmentMainBinding
import com.inzhood.mylocatyapp.service.LocatyService
import com.inzhood.mylocatyapp.utils.LineProvider

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(broadcastReceiver,  IntentFilter(
            LocatyService.KEY_ON_SENSOR_CHANGED_ACTION))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

private fun startForegroundServiceForSensors(background: Boolean) {
    val locatyIntent = Intent(requireContext(), LocatyService::class.java)
    locatyIntent.putExtra(LocatyService.KEY_BACKGROUND, background)
    ContextCompat.startForegroundService(requireContext(), locatyIntent)
}

    override fun onResume() {
        super.onResume()
        startForegroundServiceForSensors(false)
    }
    override fun onPause() {
        super.onPause()
        startForegroundServiceForSensors(true)
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
             val direction = intent.getStringExtra(LocatyService.KEY_DIRECTION)
            // SHIMON: this does the movement.
            val angle = intent.getDoubleExtra(LocatyService.KEY_ANGLE,0.0)
            val angleWithDirection = "$angle  $direction"
            binding.directionTextView.text = angleWithDirection
            // Shimon line below works OK, replacing image with line
            // binding.compassImageView.rotation = angle.toFloat() * -1
            binding.compassImageView.setImageBitmap(LineProvider.createLine(angle, 400.0))

            // get the pitch
            val pitch = intent.getDoubleExtra(LocatyService.KEY_PITCH, -1.0)
            binding.pitchTextView.text = pitch.toString()

        }

    }
}