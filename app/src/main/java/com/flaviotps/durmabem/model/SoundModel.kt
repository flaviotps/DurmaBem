package com.flaviotps.durmabem.model

import com.flaviotps.durmabem.ConfigConstants

class SoundModel( var sound:Int,
                  var volume:Float = ConfigConstants.Sound.DEFAULT_VOLUME,
                  var speed: Float = ConfigConstants.Sound.DEFAULT_SPEED,
                  var loop: Boolean = ConfigConstants.Sound.DEFAULT_LOOP){

     class SoundModelBuilder {
        private var sound:Int = 0
        private var volume:Float = ConfigConstants.Sound.DEFAULT_VOLUME
        private var speed:Float = ConfigConstants.Sound.DEFAULT_SPEED
        private var loop: Boolean = ConfigConstants.Sound.DEFAULT_LOOP

        fun withspeed(speed: Float): SoundModelBuilder {
            this.speed = speed
            return this
        }
         fun withSound(sound: Int): SoundModelBuilder {
             this.sound = sound
             return this
         }
        fun withVolume(volume: Float): SoundModelBuilder {
            this.volume = volume
            return this
        }
        fun withLoop(loop: Boolean): SoundModelBuilder {
            this.loop = loop
            return this
        }
        fun build(): SoundModel {
            return SoundModel(sound,volume,speed,loop)
        }
    }
}