package com.flaviotps.sleepwell.ui.soundMixer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.Category
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
                list.add(SoundInfo(getString(R.string.mixer_heavy_rain), R.raw.nature_heavy_rain, R.drawable.rain,  loopOffset = 1800,category = Category.RAIN))
                list.add(SoundInfo(getString(R.string.mixer_soft_rain), R.raw.nature_rain, R.drawable.rain,loopOffset = 2500, category = Category.RAIN))
                list.add(SoundInfo(getString(R.string.mixer_rain_roof), R.raw.rain_on_roof, R.drawable.rain, loopOffset = 2500,category = Category.RAIN))
                list.add(SoundInfo(getString(R.string.mixer_rain_umbrella), R.raw.rain_on_umbrella, R.drawable.rain,  loopOffset = 1800, category = Category.RAIN))
                list.add(SoundInfo(getString(R.string.mixer_rain_window), R.raw.rain_on_window, R.drawable.rain,loopOffset = 2500, category = Category.RAIN))

                list.add(SoundInfo(getString(R.string.mixer_meditation_melody), R.raw.meditation_stones, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_bell), R.raw.meditation_bells, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_bowl), R.raw.meditation_bowl, R.drawable.meditation,  loopOffset = 2000,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_flute), R.raw.meditation_flute, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_harmony), R.raw.harmony, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_hope), R.raw.hope_for_better, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_piano), R.raw.meditation_piano, R.drawable.meditation,  loopOffset = 1800,category = Category.MEDITATION))
                list.add(SoundInfo(getString(R.string.mixer_meditation_windchimes), R.raw.meditation_wind_chimes, R.drawable.meditation,  loopOffset = 2500,category = Category.MEDITATION))

                list.add(SoundInfo(getString(R.string.mixer_ocean), R.raw.ocean, R.drawable.water,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_wind), R.raw.nature_wind, R.drawable.wind,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_thunder), R.raw.nature_thunder, R.drawable.thunder,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_forest), R.raw.nature_forest, R.drawable.forest,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_water_flow), R.raw.nature_water_flow, R.drawable.waterflow,  loopOffset = 2000,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_night), R.raw.night, R.drawable.night,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_farm), R.raw.farm, R.drawable.farm,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_snow), R.raw.snow, R.drawable.snow,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_waterfall), R.raw.waterfall, R.drawable.waterfall,  loopOffset = 1800,category = Category.NATURE))
                list.add(SoundInfo(getString(R.string.mixer_firewood), R.raw.nature_firewood, R.drawable.fire, loopOffset = 3000,category = Category.NATURE))

                list.add(SoundInfo(getString(R.string.mixer_crowded), R.raw.crowd, R.drawable.crowd,  loopOffset = 1800,category = Category.URBAN))
                list.add(SoundInfo(getString(R.string.mixer_city_rails), R.raw.city_rails, R.drawable.rails,  loopOffset = 1800,category = Category.URBAN))
                list.add(SoundInfo(getString(R.string.mixer_keyboard), R.raw.city_keyboard, R.drawable.keyboard,  loopOffset = 1800,category = Category.URBAN))
                list.add(SoundInfo(getString(R.string.mixer_clock), R.raw.instrument_clock, R.drawable.clock,loopOffset = 0,category = Category.URBAN))

                list.add(SoundInfo(getString(R.string.mixer_forest_birds), R.raw.animal_bird, R.drawable.bird,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_birds), R.raw.bird2, R.drawable.bird,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_birds2), R.raw.bird2, R.drawable.bird,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_crickets), R.raw.animal_cricket, R.drawable.cricket,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_frog), R.raw.animal_frog, R.drawable.frog,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_cicada), R.raw.cicada, R.drawable.cicada,category = Category.ANIMAL))
                list.add(SoundInfo(getString(R.string.mixer_whale), R.raw.whale, R.drawable.whale,category = Category.ANIMAL))

                list.add(SoundInfo(getString(R.string.mixer_white_noise), R.raw.white_noise, R.drawable.noise,  loopOffset = 1800,category = Category.NOISE))
                list.add(SoundInfo(getString(R.string.mixer_brown_noise), R.raw.brown_noise, R.drawable.noise,loopOffset = 3000,category = Category.NOISE))
                list.add(SoundInfo(getString(R.string.mixer_pink_noise), R.raw.pink_noise, R.drawable.noise,loopOffset = 1700,category = Category.NOISE))

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