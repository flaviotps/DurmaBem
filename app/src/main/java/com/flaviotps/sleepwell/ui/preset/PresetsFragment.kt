package com.flaviotps.sleepwell.ui.preset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.Category
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.adapter.PresetsGridAdapter
import com.flaviotps.sleepwell.base.BaseServiceActivity
import com.flaviotps.sleepwell.repository.PresetRepository
import com.flaviotps.sleepwell.services.SoundService
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_presets.*


class PresetsFragment : Fragment() {
    private lateinit var adapter: PresetsGridAdapter
    private lateinit var presetRepository: PresetRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         context?.let { presetRepository = PresetRepository(it) }

        (activity as BaseServiceActivity).onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                setupPresets(binder)

                val onCheckedChangeListener =
                    ChipGroup.OnCheckedChangeListener { _, i ->
                        context?.let {context->
                            when(i){
                                Category.RAIN.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.RAIN))
                                }
                                Category.NATURE.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.NATURE))
                                }
                                Category.ANIMAL.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.ANIMAL))
                                }
                                Category.URBAN.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.URBAN))
                                }
                                Category.NOISE.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.NOISE))
                                }
                                Category.MEDITATION.value -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.MEDITATION))
                                }
                                -1 -> {
                                    adapter = PresetsGridAdapter(context, binder, presetRepository.getPresets(Category.NONE))
                                }
                            }

                            gridViewPresets.adapter = adapter
                            gridViewPresets.invalidate()
                            adapter.notifyDataSetInvalidated()
                        }
                   }

                chipsGroup.setOnCheckedChangeListener(onCheckedChangeListener)
            }
        })
    }

    private fun setupPresets(binder: SoundService.SoundServiceBinder){
        context?.let {
            adapter = PresetsGridAdapter(it, binder, presetRepository.getPresets(Category.NONE))
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