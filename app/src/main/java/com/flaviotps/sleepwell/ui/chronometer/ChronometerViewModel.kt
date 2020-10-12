package com.flaviotps.sleepwell.ui.chronometer

import androidx.lifecycle.ViewModel

class ChronometerViewModel: ViewModel() {

      var chronometerTime: Long = 0

    fun minutesToMilliseconds(minutes:Int) {
        chronometerTime = minutes.toLong()*60*1000
    }

    fun hoursToMilliseconds(hours:Int) {
        chronometerTime = hours.toLong()*60*60*1000
    }
}