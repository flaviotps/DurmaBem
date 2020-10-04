package com.flaviotps.sleepwell.model

class SoundPool(
    var author:String? = null,
    var title:String? = null,
    var image:Int? = null, vararg sounds: SoundPlayer){
    var sounds:MutableList<SoundPlayer> = sounds.toMutableList()
}
