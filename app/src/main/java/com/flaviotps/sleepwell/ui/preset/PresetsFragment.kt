package com.flaviotps.sleepwell.ui.preset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.adapter.PresetsGridAdapter
import com.flaviotps.sleepwell.base.BaseServiceActivity
import com.flaviotps.sleepwell.repository.PresetRepository
import com.flaviotps.sleepwell.services.SoundService
import kotlinx.android.synthetic.main.fragment_presets.*

class PresetsFragment : Fragment() {
    private lateinit var adapter: PresetsGridAdapter
    private lateinit var presetRepository: PresetRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presetRepository = PresetRepository()

        (activity as BaseServiceActivity).onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                setupPresets(binder)
            }
        })
    }

    private fun setupPresets(binder: SoundService.SoundServiceBinder){
        context?.let {
            adapter = PresetsGridAdapter(it, binder, presetRepository.getPresets())
            gridViewPresets.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presets, container, false)
    }
}