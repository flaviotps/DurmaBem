package com.flaviotps.sleepwell.ui.main

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flaviotps.sleepwell.NAVIGATE_TO
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.base.BaseServiceActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class MainActivity : BaseServiceActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()
        initPlayerToolbar()
        handleIntent()
    }

    private fun initNavController() {
        navController = findNavController(R.id.navFragment)
        bottomNavigationView.setupWithNavController(navController)
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
            toolbar_play.setOnClickListener {
                binder?.getService()?.getPoolManager()?.apply {
                    if (isPlayingAny()) {
                        pauseAll()
                    } else {
                        playAll(this@MainActivity)
                    }
                }
            }
            toolbar_stop.setOnClickListener {
                binder?.getService()?.getPoolManager()?.apply {
                    stopAll()
                }
            }
            binder?.getService()?.getPoolManager()?.isAnyPlayingLiveData()?.observe(this, Observer {playing ->
                val playAndPause = supportActionBar?.customView?.findViewById<ImageButton>(R.id.toolbar_play)
                if(playing){
                    playAndPause?.setImageResource(R.drawable.ic_pause_black_24dp)
                }else{
                    playAndPause?.setImageResource(R.drawable.ic_play_arrow_white_24dp)
                }
            })
        })
    }

    private fun handleIntent(){
        intent.getIntExtra(NAVIGATE_TO, R.id.presetsFragment).let {
            navController.navigate(it)
        }
    }
}
