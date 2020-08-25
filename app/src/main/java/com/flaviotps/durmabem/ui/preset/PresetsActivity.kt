package com.flaviotps.durmabem.ui.preset

import android.media.MediaPlayer
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

class PresetsActivity : BaseServiceActivity() {

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
        soundPool.sounds.add(SoundPlayer(SoundInfo("FIRE",R.raw.fire,R.drawable.fire), MediaPlayer.create(this,R.raw.fire)))

        val soundPool2 = SoundPool()
        soundPool2.sounds.add(SoundPlayer(SoundInfo("RAIN",R.raw.rain,R.drawable.rain), MediaPlayer.create(this,R.raw.rain)))

        list.add(soundPool)
        list.add(soundPool2)
        gridViewPresets.adapter = PresetsGridAdapter(this,binder.getMediaPlayerPool(),list)
    }
}
