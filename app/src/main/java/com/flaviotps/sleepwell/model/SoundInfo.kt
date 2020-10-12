package com.flaviotps.sleepwell.model

import com.flaviotps.sleepwell.Category

data class SoundInfo(val soundName:String, val soundResource:Int, val soundIcon: Int, var volume: Float = 0.5f,var loopOffset: Int = 2000, var category: Category = Category.NONE)