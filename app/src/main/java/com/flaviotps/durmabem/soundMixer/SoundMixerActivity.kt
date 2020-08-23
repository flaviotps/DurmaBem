package com.flaviotps.durmabem.soundMixer

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.adapter.SoundGridAdapter
import com.flaviotps.durmabem.main.viewmodel.ServiceViewModel
import com.flaviotps.durmabem.custom.commons.BaseActivity
import com.flaviotps.durmabem.custom.model.SoundModel
import kotlinx.android.synthetic.main.activity_sound_mixer.*

class SoundMixerActivity : BaseActivity() {

    private lateinit var viewModel: ServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_mixer)

        viewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        startSoundService(viewModel.serviceConnection)

        viewModel.onServiceAvailable().observe(this, Observer {
                it?.let { binder ->
                    val list = mutableListOf<SoundModel>()
                    list.add(SoundModel("RAIN",R.raw.rain,R.drawable.rain))
                    list.add(SoundModel("FIRE",R.raw.fire,R.drawable.fire))
                    gridViewSounds.adapter = SoundGridAdapter(this,binder.getMediaPlayerPool(), list)
                }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(viewModel.serviceConnection)
    }
}
