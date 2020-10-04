package com.flaviotps.sleepwell.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.flaviotps.sleepwell.R
import kotlinx.android.synthetic.main.item_sound_button.view.*


class SoundButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    interface VolumeListener{
      fun onVolumeChanged(volume:Float)
    }

    private var active = false

    init {
        LayoutInflater.from(context).inflate(R.layout.item_sound_button, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.SoundButton, 0, 0).let {
            active = it.getBoolean(R.styleable.SoundButton_active, false)
            isEnabled = it.getBoolean(R.styleable.SoundButton_android_enabled, true)
            icon.setImageDrawable(it.getDrawable(R.styleable.SoundButton_icon))
            label.text = it.getText(R.styleable.SoundButton_android_text)
        }
        isClickable = true
        isFocusable = true

        volumeBar.max = MAX_VOLUME.toInt()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        background = ContextCompat.getDrawable(context, R.drawable.background)
        children.forEach { it.isEnabled = enabled }
        refreshDrawableState()
    }

    private fun toggle(active: Boolean) {
        background = if (active) {
            ContextCompat.getDrawable(context, R.drawable.button_sound_selected)
        } else {
            ContextCompat.getDrawable(context, R.drawable.button_sound_normal)
        }
    }
    override fun performClick(): Boolean {
        if(isEnabled) {
            active = !active
            toggle(active)
        }
        return super.performClick()
    }

    fun setText(text:String){
        label.text = text
    }

    fun setIcon(res:Int){
        icon.setImageDrawable(context.getDrawable(res))
    }

    fun isActive(): Boolean {
        return active
    }

    fun setSeekBarProgress(progress: Float){
        volumeBar.progress = (progress*MAX_VOLUME).toInt()
    }

    fun setActive(active: Boolean){
        this.active = active
        toggle(active)
    }

    fun setVolumeListener(volumeListener: VolumeListener){
        volumeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volumeListener.onVolumeChanged((progress.toFloat()/MAX_VOLUME))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    companion object {
        private const val MAX_VOLUME = 100f
    }
}