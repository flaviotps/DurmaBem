package com.flaviotps.sleepwell.ui.chronometer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.base.BaseServiceActivity
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_chronometer.*
import java.util.concurrent.TimeUnit

class ChronometerFragment : Fragment() {

    lateinit var viewModel: ChronometerViewModel

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)

        (activity as BaseServiceActivity).onServiceAvailable().observe(this, Observer {
            it?.let { binder ->
                binder.getService().getChronometerTime().observe(this, Observer { time ->
                    chip_group.children.forEach {c ->
                        c.isEnabled = time <= 0
                    }

                    if(time > 0){
                        timerButton.text = getString(R.string.stop)
                    }else{
                        timerButton.text = getString(R.string.start)
                        timeLeft.text = "0"
                    }

                   val timeRemaining = String.format("%02d:%02d:%02d",
                       TimeUnit.MILLISECONDS.toHours(time),
                       TimeUnit.MILLISECONDS.toMinutes(time) -
                               TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                       TimeUnit.MILLISECONDS.toSeconds(time) -
                               TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)))
                    timeLeft.text = timeRemaining
                })


                timerButton.setOnClickListener {
                    binder.getService().getChronometerTime().value?.let { time ->
                        if(time > 0){
                            binder.getService().stopTimer()
                        }else{
                            binder.getService().startTimer(viewModel.chronometerTime)
                        }
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onCheckedChangeListener =
            ChipGroup.OnCheckedChangeListener { _, i ->
                timerButton.isEnabled = i != -1
                    when(i){
                        R.id.min5 -> {
                            viewModel.minutesToMilliseconds(5)
                        }
                        R.id.min15 -> {
                            viewModel.minutesToMilliseconds(15)
                        }
                        R.id.min30 -> {
                            viewModel.minutesToMilliseconds(30)
                        }
                        R.id.h1 -> {
                            viewModel.hoursToMilliseconds(1)
                        }
                        R.id.h2 -> {
                            viewModel.hoursToMilliseconds(2)
                        }
                        R.id.h3 -> {
                            viewModel.hoursToMilliseconds(3)
                        }
                        R.id.h4 -> {
                            viewModel.hoursToMilliseconds(4)
                        }
                        R.id.h5 -> {
                            viewModel.hoursToMilliseconds(5)
                        }
                        R.id.h6 -> {
                            viewModel.hoursToMilliseconds(6)
                        }
                        R.id.h7 -> {
                            viewModel.hoursToMilliseconds(7)
                        }
                        R.id.h8 -> {
                            viewModel.hoursToMilliseconds(8)
                        }
                         -1 -> {
                             viewModel.minutesToMilliseconds(0)
                         }
                    }
            }


        chip_group.setOnCheckedChangeListener(onCheckedChangeListener)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chronometer, container, false)
    }
}