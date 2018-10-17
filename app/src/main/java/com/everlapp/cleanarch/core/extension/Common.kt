package com.everlapp.cleanarch.core.extension

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager



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