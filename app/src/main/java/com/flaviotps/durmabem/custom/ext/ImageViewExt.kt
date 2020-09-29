package com.flaviotps.durmabem.custom.ext

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat


fun ImageView.setFromResource(context:Context, resId:Int){
    val drawable = ContextCompat.getDrawable(context, resId)
    this.setImageDrawable(drawable)
}
