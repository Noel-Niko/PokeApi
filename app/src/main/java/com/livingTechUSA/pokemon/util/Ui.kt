package com.livingTechUSA.pokemon.util


import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.livingTechUSA.pokemon.R

class Ui {
    companion object {

        @JvmStatic
        fun dismissKeyboard(activity: Activity) {
            activity.currentFocus?.let {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }

        @JvmStatic
        fun showSoftKeyBoard(activity: Activity, editBox: EditText){
            val inputMethodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            editBox.requestFocus()
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
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