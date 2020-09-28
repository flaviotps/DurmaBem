package com.flaviotps.durmabem.ui.preset

import android.os.Bundle
import androidx.lifecycle.Observer
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.adapter.PresetsGridAdapter
import com.flaviotps.durmabem.custom.commons.BaseServiceActivity
import com.flaviotps.durmabem.custom.model.SoundInfo
import com.flaviotps.durmabem.custom.model.SoundPlayer
import com.flaviotps.durmabem.custom.model.SoundPool
import com.flaviotps.durmabem.custom.services.SoundService
import kotlinx.android.synthetic.main.activity_presets.*

class PresetsActivity : BaseServiceActivity()
{
    lateinit var adapter: PresetsGridAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presets)

        onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                    setupPresets(binder)
            }
        })
    }

    private fun setupPresets(binder:SoundService.SoundServiceBinder){
        val list = mutableListOf<SoundPool>()
        val soundPool = SoundPool()
        val soundPool2 = SoundPool()

            soundPool.sounds.add(SoundPlayer(PresetsActivity@this ,SoundInfo("Thunder",R.raw.thunder,R.drawable.fire)))
            soundPool.title = "Thunder"
            list.add(soundPool)

            soundPool2.sounds.add(SoundPlayer(PresetsActivity@this,SoundInfo("Cafe",R.raw.cafe,R.drawable.rain)))
            soundPool2.title = "Cafe"
            list.add(soundPool2)

        adapter = PresetsGridAdapter(this,binder,list)
        gridViewPresets.adapter = adapter
    }
}
