package com.flaviotps.durmabem.soundMixer

import android.os.Bundle
import androidx.lifecycle.Observer
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.adapter.SoundGridAdapter
import com.flaviotps.durmabem.custom.commons.BaseServiceActivity
import com.flaviotps.durmabem.custom.model.SoundInfo
import kotlinx.android.synthetic.main.activity_sound_mixer.*

class SoundMixerServiceActivity : BaseServiceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_mixer)

        onServiceAvailable().observe(this, Observer {
                it?.let { binder ->
                    val list = mutableListOf<SoundInfo>()
                    list.add(SoundInfo("RAIN",R.raw.rain,R.drawable.rain))
                    list.add(SoundInfo("FIRE",R.raw.fire,R.drawable.fire))
                    gridViewSounds.adapter = SoundGridAdapter(this,binder.getMediaPlayerPool(), list)
                }
        })
    }
}
