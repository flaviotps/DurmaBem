package com.flaviotps.sleepwell.ext

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children

fun ConstraintLayout.addAllOnClickListener(listener: View.OnClickListener?){
    children.forEach {
        it.setOnClickListener(listener)
    }
}