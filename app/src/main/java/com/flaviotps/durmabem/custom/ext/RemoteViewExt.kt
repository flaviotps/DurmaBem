package com.flaviotps.durmabem.custom.ext

import android.content.res.Resources
import android.widget.RemoteViews

fun RemoteViews.setDrawableById(
    layoutRes: Int,
    imageName: String?,
    resources: Resources,
    packageName: String
) {
    imageName.let {
        this.setImageViewResource(
            layoutRes,
            resources.getIdentifier(it, "drawable", packageName)
        )
    }
}