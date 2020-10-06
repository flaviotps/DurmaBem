package com.flaviotps.sleepwell.ui.main

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.base.BaseServiceActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class MainActivity : BaseServiceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPlayerToolbar()
        bottomNavigationView.setupWithNavController(findNavController(R.id.navFragment))
    }

    private fun initPlayerToolbar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_custom)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar_title.text = resources.getString(R.string.app_name)
        togglePlayPause()
    }

    private fun togglePlayPause(){
        onServiceAvailable().observe(this, Observer {binder ->
            toolbar_playAndPause.setOnClickListener { binder?.getService()?.getMediaPoolManager()?.apply {
                if(isPlayingAny()){
                    stopAll()
                }else{
                    playAll(this@MainActivity)
                }
            } }
            binder?.getService()?.getMediaPoolManager()?.isAnyPlayingLiveData()?.observe(this, Observer {playing ->
                val playAndPause = supportActionBar?.customView?.findViewById<ImageButton>(R.id.toolbar_playAndPause)
                if(playing){
                    playAndPause?.setImageResource(R.drawable.ic_pause_black_24dp)
                }else{
                    playAndPause?.setImageResource(R.drawable.ic_play_arrow_white_24dp)
                }
            })
        })
    }
}
