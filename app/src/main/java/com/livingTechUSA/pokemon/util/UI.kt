package com.livingTechUSA.pokemon.util


import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.livingTechUSA.pokemon.R

class UI {
    companion object {

        /**
         * Return @ColorInt base on build version
         */
        @ColorInt
        fun getColorInt(context: Context, @ColorRes color: Int): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.resources.getColor(color, null)
            } else {
                context.resources.getColor(color)
            }
        }

        fun isTablet(context: Context): Boolean {
            return context.resources.getBoolean(R.bool.is_tablet)
        }

        fun isLandscape(context: Context): Boolean {
            return context.resources.getBoolean(R.bool.is_landscape)
        }

        fun isPortrait(context: Context): Boolean {
            return context.resources.getBoolean(R.bool.is_portrait)
        }

        fun getMeasuredHeight(view: View): Int {
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            return view.measuredHeight
        }

    }

}