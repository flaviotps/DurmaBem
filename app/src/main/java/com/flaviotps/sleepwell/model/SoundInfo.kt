package com.flaviotps.sleepwell.model

data class SoundInfo(val soundName:String, val soundResource:Int, val soundIcon: Int, var volume: Float = 0.5f,var loopOffset: Int = 0)