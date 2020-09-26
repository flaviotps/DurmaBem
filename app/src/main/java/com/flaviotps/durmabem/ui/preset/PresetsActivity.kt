package com.flaviotps.durmabem.ui.preset

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
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

            soundPool.sounds.add(SoundPlayer(PresetsActivity@this ,SoundInfo("FIRE",R.raw.thunder,R.drawable.fire)))
            list.add(soundPool)

            soundPool.sounds.add(SoundPlayer(PresetsActivity@this,SoundInfo("RAIN",R.raw.rain,R.drawable.rain)))
            list.add(soundPool)

        adapter = PresetsGridAdapter(this,binder.getMediaPlayerPool(),list)
        gridViewPresets.adapter = adapter
    }
}
