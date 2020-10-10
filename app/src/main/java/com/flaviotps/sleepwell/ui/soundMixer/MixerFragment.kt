package com.flaviotps.sleepwell.ui.soundMixer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.adapter.SoundGridAdapter
import com.flaviotps.sleepwell.base.BaseServiceActivity
import com.flaviotps.sleepwell.model.SoundInfo
import kotlinx.android.synthetic.main.fragment_mixer.*

class MixerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as BaseServiceActivity).onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                val list = mutableListOf<SoundInfo>()
                list.add(SoundInfo("Rain", R.raw.nature_rain, R.drawable.rain))
                list.add(SoundInfo("Storm", R.raw.nature_heavy_rain, R.drawable.storm))
                list.add(SoundInfo("Night", R.raw.night, R.drawable.night))
                list.add(SoundInfo("Forest", R.raw.nature_forest, R.drawable.forest))
                list.add(SoundInfo("Crowded", R.raw.crowd, R.drawable.crowd))
                list.add(SoundInfo("Firewood", R.raw.nature_firewood, R.drawable.fire))
                list.add(SoundInfo("Snow", R.raw.snow, R.drawable.snow))
                list.add(SoundInfo("Wind", R.raw.nature_wind, R.drawable.wind))
                list.add(SoundInfo("Waterfall", R.raw.waterfall, R.drawable.waterfall))
                list.add(SoundInfo("Ocean", R.raw.ocean, R.drawable.water))
                list.add(SoundInfo("Farm", R.raw.farm, R.drawable.farm))
                list.add(SoundInfo("Water Flow", R.raw.nature_water_flow, R.drawable.waterflow))
                gridViewSounds.adapter =
                    context?.let { ctx -> binder.getService().getPoolManager().let { mpm ->
                        SoundGridAdapter(ctx, mpm, list)
                    } }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mixer, container, false)
    }
}