package com.flaviotps.durmabem.ui.preset

import android.os.Bundle
import androidx.lifecycle.Observer
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.PresetRepository
import com.flaviotps.durmabem.custom.adapter.PresetsGridAdapter
import com.flaviotps.durmabem.custom.commons.BaseServiceActivity
import com.flaviotps.durmabem.custom.services.SoundService
import kotlinx.android.synthetic.main.activity_presets.*


class PresetsActivity : BaseServiceActivity()
{
    private lateinit var adapter: PresetsGridAdapter
    private lateinit var presetRepository: PresetRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presets)

        presetRepository = PresetRepository()

        onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                setupPresets(binder)
            }
        })
    }

    private fun setupPresets(binder: SoundService.SoundServiceBinder){


        adapter = PresetsGridAdapter(this, binder, presetRepository.getPresets())
        gridViewPresets.adapter = adapter
    }
}
