package com.everlapp.cleanarch.core.extension

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


// ----------- Hide keyboard ------------------------------

fun Activity.hideKeyboard() {
    val inputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}


fun Fragment.hideKeyboard() {
    val inputMethodManager =
            this.activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.activity?.currentFocus?.windowToken, 0)
}


/**
 * Get colorResource from color resource
 */
fun Activity.getColorExt(@ColorRes colorResource: Int) : Int = ActivityCompat.getColor(this, colorResource)
